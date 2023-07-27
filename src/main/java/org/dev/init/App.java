package seu.pacote;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Carrega o arquivo FXML da tela de login
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));

        // Cria a cena com a tela de login
        Scene scene = new Scene(root);

        // Configura o título da janela
        primaryStage.setTitle("Tela de Login");

        // Define a cena na janela principal e exibe
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
