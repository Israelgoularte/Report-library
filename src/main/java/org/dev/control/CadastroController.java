package org.dev.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.dev.service.MyServicesDAO;
import org.dev.service.fxelement.DbOptionsGenerationMyServices;
import org.dev.service.usuario.CadastrarNovoUsuarioMyServices;
import org.dev.service.usuario.ValidaDadosUsuario;
import org.dev.util.ExceptionMensagen;
import org.dev.util.FormataTextElementToIntenger;
import org.dev.util.menssagensInternas.GenericMenssage;
import org.dev.util.tarefas.ServiceTask;
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

        diaNascimento.setTextFormatter(FormataTextElementToIntenger.createTextFormatter(2, 31));
        mesNascimento.setTextFormatter(FormataTextElementToIntenger.createTextFormatter(2,12));
        anoNascimento.setTextFormatter(FormataTextElementToIntenger.createTextFormatter(4,2100));

        GenericMenssage<Boolean,String> addDbChoiceOptionsResult = new DbOptionsGenerationMyServices(selectServer).execute();
        if (addDbChoiceOptionsResult.getMenssageOne()){
            //possibilidade de tratativa de erros
        }

    }



    @FXML
    public void cadastrar() {

        progress.setVisible(true);
        warningBox.setVisible(true);
        warningBox.getChildren().remove(warning);
        progress.setStyle("-fx-accent: #e3d70d;");
        DoubleProperty prog = new SimpleDoubleProperty(0.0);
        progress.progressProperty().bind(prog);


        String email = emailField.getText();
        String usuario = usuarioField.getText().toLowerCase();
        String senha = senhaField.getText();
        String nome = nomeField.getText();
        String dia = diaNascimento.getText();
        String mes = mesNascimento.getText();
        String ano = anoNascimento.getText();

        prog.setValue(0.1);

        GenericMenssage<Boolean,String> validaDadosResult = new ValidaDadosUsuario(usuario,email,senha,nome,dia,mes,ano).execute();

        if (validaDadosResult.getMenssageOne()){
            prog.setValue(0.2);

            MyServicesDAO service = new CadastrarNovoUsuarioMyServices(usuario,senha,email,nome,ano,mes,dia);
            ServiceTask task = new ServiceTask(service,progress);

            task.setOnSucceeded(e -> {
            if (task.getValue()) {
                ViewSimpleFactory.createView("LOGIN");
            } else {
                progress.setStyle("-fx-accent: #ff0e13;");
                String menssagen = task.getMessage();
                warning.setText(ExceptionMensagen.simpleMenssage(menssagen ));
                warningBox.getChildren().add(1,warning);
            }
            });

            task.setOnFailed(e -> {
                progress.setStyle("-fx-accent: #ff0e13;");
                String menssagen = task.getMessage();
                warning.setText(ExceptionMensagen.simpleMenssage(menssagen));
                warningBox.getChildren().add(1,warning);
            });

            prog.setValue(0.3);
            progress.progressProperty().bind(task.progressProperty());
            Thread thread = new Thread(task);
            thread.setDaemon(true); // Define a thread como daemon para que ela não impeça o encerramento da aplicação
            thread.start();
        }else{
            warning.setText(validaDadosResult.getMenssageTwo());
            warningBox.getChildren().add(warning);
            progress.setStyle("-fx-accent: #ff0e13;");
            progress.setProgress(1);
        }

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

