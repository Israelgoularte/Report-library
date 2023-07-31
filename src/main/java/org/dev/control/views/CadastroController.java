package org.dev.control.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import org.dev.control.service.NovoCadastroTask;
import org.dev.control.service.boxCreators.ChoiceBDFactory;
import org.dev.util.ExceptionMensagen;
import org.dev.util.ValidaEmail;
import org.dev.util.ValidaSenha;
import org.dev.view.ViewSimpleFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private TextField usuarioField;

    @FXML
    private TextField mesNascimento;

    @FXML
    private TextField diaNascimento;

    @FXML
    private TextField anoNascimento;


    @FXML
    private VBox centerbox;

    @FXML
    private ProgressBar progress;

    @FXML
    private ChoiceBox<String> selectServer;


    private Label warning;

    @FXML
    private VBox warningBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        warning = new Label();
        warning.setWrapText(true);
        warning.getStyleClass().add("label-warning");

        centerbox.setMaxHeight(400);

        diaNascimento.setTextFormatter(createTextFormatter(2,31));
        mesNascimento.setTextFormatter(createTextFormatter(2,12));
        anoNascimento.setTextFormatter(createTextFormatter(4,2100));

        ChoiceBDFactory.addDataBaseChoices(selectServer);

    }

    private TextFormatter<Integer> createTextFormatter(int length,int max){
        return new TextFormatter<>(new IntegerStringConverter(), null, c -> {
            String newText = c.getControlNewText();
            if (newText.matches("\\d{0,"+length+"}")) {
                // Verifica se o valor está dentro do intervalo permitido (0 a 12)
                int value = newText.isEmpty() ? 0 : Integer.parseInt(newText);
                if (value >= 0 && value <= max) {
                    return c;
                }
            }
            return null;
        });
    }

    @FXML
    public void cadastrar() {

        progress.setVisible(true);
        warningBox.setVisible(true);
        warningBox.getChildren().remove(warning);

        String email = emailField.getText();
        String usuario = usuarioField.getText().toLowerCase();
        String senha = senhaField.getText();
        String nome = nomeField.getText();
        String dia = diaNascimento.getText();
        String mes = mesNascimento.getText();
        String ano = anoNascimento.getText();

        NovoCadastroTask task = new NovoCadastroTask(usuario,email,senha,nome,dia,mes,ano,progress);
        progress.setStyle("-fx-accent: #e3d70d;");

        task.setOnSucceeded(e -> {
            if (task.getValue()) {
                ViewSimpleFactory.createView("LOGIN");
            } else {
                progress.setStyle("-fx-accent: #ff0e13;");
                String[] menssagen = task.getMessage().split(",",2);
                if(menssagen[0].equals("0")){
                    warning.setText(ExceptionMensagen.simpleMenssage(menssagen[1],"Cadastro" ));
                }else warning.setText(menssagen[1]);
                warningBox.getChildren().add(1,warning);
            }
        });

        task.setOnFailed(e -> {
            progress.setStyle("-fx-accent: #ff0e13;");
            String[] menssagen = task.getMessage().split(",",1);
            if(menssagen[0].equals("0")){
                warning.setText(ExceptionMensagen.simpleMenssage(menssagen[1],"Cadastro" ));
            }else warning.setText(menssagen[1]);
            warningBox.getChildren().add(1,warning);
        });

        progress.progressProperty().bind(task.progressProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true); // Define a thread como daemon para que ela não impeça o encerramento da aplicação
        thread.start();

    }

    public boolean validaDados(String usuario, String email, String senha, String nome){

        int index =0;
        String menssagem ="";
        if(usuario.equals("")) {
            menssagem += "Insira um usuario valido";
            usuarioField.requestFocus();
            index=1;
        }else if(!ValidaEmail.isValidEmail(email)) {
            menssagem += "Insira um email valido";
            emailField.requestFocus();
            index=2;
        }else if(!ValidaSenha.isStrongPassword(senha)) {
            menssagem += "A senha deve ter no mínimo 8 caracteres e incluir:\n" +
                    "- Pelo menos uma letra maiúscula\n" +
                    "- Pelo menos uma letra minúscula\n" +
                    "- Pelo menos um número\n" +
                    "- Pelo menos um caractere especial (!@#$%^&*()_-=[]{};':\"\\|,.<>/?)";
            senhaField.requestFocus();
            index=3;
        }else if(nome.equals("")) {
            menssagem += "Insira um nome valido";
            nomeField.requestFocus();
            index=4;
        }else if(diaNascimento.getText().length()>2 || diaNascimento.getText().equals("")) {
            menssagem += "Insira uma data valida valido";
            diaNascimento.requestFocus();
            index=5;
        }else if (mesNascimento.getText().length()>2 || mesNascimento.getText().equals("")) {
            menssagem += "Insira uma data valida valido";
            mesNascimento.requestFocus();
            index=6;
        }else if(anoNascimento.getText().length()!=4){
            menssagem += "Insira uma data valida valido";
            anoNascimento.requestFocus();
            index=7;
        }else return true;

        warning.setText(menssagem);
        centerbox.getChildren().add(index,warning);
        return false;
    }

    private void clearFields(){
        usuarioField.setText("");
        emailField.setText("");
        senhaField.setText("");
        nomeField.setText("");
        diaNascimento.setText("");
        mesNascimento.setText("");
        anoNascimento.setText("");
    }


    @FXML
    public void voltar(){
        ViewSimpleFactory.createView("LOGIN");
    }
}

