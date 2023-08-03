package org.dev.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.dev.service.MyServicesDAO;
import org.dev.service.fxelement.DbOptionsGenerationMyServices;
import org.dev.service.usuario.LoginMyServices;
import org.dev.util.ExceptionMensagen;
import org.dev.util.TruncateDataBase;
import org.dev.util.menssagensInternas.GenericMenssage;
import org.dev.util.tarefas.ServiceTask;
import org.dev.view.ViewSimpleFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Label warning;

    @FXML
    private VBox warningBox;

    @FXML
    private ChoiceBox<String> selectServer;

    @FXML
    private ProgressBar progress;

    public LoginController(){
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login();
            }
        });
        warning = new Label();
        warning.setWrapText(true);
        warning.getStyleClass().add("label-warning");

        GenericMenssage<Boolean,String> executionMenssage = new DbOptionsGenerationMyServices(selectServer).execute();

        if(executionMenssage.getMenssageOne()){
            //pode ser adicionar um alerta caso não seja feito a leitura do arquivo XML de forma correta.
        }

    }

    @FXML
    public void login()  {
        // Antes de iniciar a tarefa, defina a ProgressBar como visível
        progress.setVisible(true);
        warningBox.setVisible(true);
        warningBox.getChildren().remove(warning);
        progress.setStyle("-fx-accent: #e3d70d;");

        DoubleProperty prog = new SimpleDoubleProperty(0.1);

        progress.progressProperty().bind(prog);



        String username = usernameField.getText();
        String password = passwordField.getText();

        MyServicesDAO service = new LoginMyServices(username,password);

        prog.setValue(0.2);
        ServiceTask task = new ServiceTask(service,progress);

        task.setOnSucceeded(e -> {
            if (task.getValue()) {
                ViewSimpleFactory.createView("HOME");
            } else {
                failedTask(task);
            }
        });

        task.setOnFailed(e -> {
            failedTask(task);
        });
        prog.setValue(0.3);
        progress.progressProperty().bind(task.progressProperty());
        Thread thread = new Thread(task);
        thread.setDaemon(true); // Define a thread como daemon para que ela não impeça o encerramento da aplicação
        thread.start();
    }

    private void failedTask(ServiceTask task) {
        progress.setStyle("-fx-accent: #ff0e13;");
        String simpleMenssage = ExceptionMensagen.simpleMenssage(task.getMessage());
        warning.setText(simpleMenssage);
        warningBox.getChildren().add(1,warning);
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocus();
    }

    @FXML
    public void signup() throws IOException {
        ViewSimpleFactory.createView("CADASTRO");
    }

    // Método para realizar a autenticação do usuário

    @FXML
    public void truncade(){
        TruncateDataBase.truncade();
    }
}
