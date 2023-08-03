package org.dev.init;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.dev.util.contexto.StageContexto;
import org.dev.view.ViewSimpleFactory;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        StageContexto.getInstance().setContexto(primaryStage);

        primaryStage.setTitle("Report Library");
        Image iconImage = new Image(getClass().getResourceAsStream("/view/css/img/icone2.png"));
        primaryStage.getIcons().add(iconImage);

        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(720);
        ViewSimpleFactory.createView("LOGIN");

    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}
