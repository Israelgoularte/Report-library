package org.dev.control.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.dev.control.service.AuthenticationTask;
import org.dev.control.service.boxCreators.ChoiceBDFactory;
import org.dev.util.ExceptionMensagen;
import org.dev.util.TruncateDataBase;
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

        ChoiceBDFactory.addDataBaseChoices(selectServer);
    }

    @FXML
    public void login()  {
        // Antes de iniciar a tarefa, defina a ProgressBar como visível
        progress.setVisible(true);
        warningBox.setVisible(true);
        warningBox.getChildren().remove(warning);

        AuthenticationTask authenticationTask = new AuthenticationTask(usernameField.getText().toLowerCase(), passwordField.getText(), progress);
        progress.setStyle("-fx-accent: #e3d70d;");

        authenticationTask.setOnSucceeded(e -> {
            if (authenticationTask.getValue()) {
                ViewSimpleFactory.createView("LINKS");
            } else {
                progress.setStyle("-fx-accent: #ff0e13;");
                warning.setText(ExceptionMensagen.simpleMenssage(authenticationTask.getMessage(),"Usuario" ));
                warningBox.getChildren().add(1,warning);
                usernameField.setText("");
                passwordField.setText("");
                usernameField.requestFocus();
            }
        });

        authenticationTask.setOnFailed(e -> {
            progress.setStyle("-fx-accent: #ff0e13;");
            warning.setText(ExceptionMensagen.simpleMenssage(authenticationTask.getException().getMessage(), "Usuario"));
            warningBox.getChildren().add(1,warning);
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus();
        });

        progress.progressProperty().bind(authenticationTask.progressProperty());

        Thread thread = new Thread(authenticationTask);
        thread.setDaemon(true); // Define a thread como daemon para que ela não impeça o encerramento da aplicação
        thread.start();
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
