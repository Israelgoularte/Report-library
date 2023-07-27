package org.dev.init;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.dev.control.StageController;
import org.dev.view.ViewSimpleFactory;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        StageController.getInstance().setStage(primaryStage);

        primaryStage.setTitle("Software WareHouse");
        Image iconImage = new Image(getClass().getResourceAsStream("/img/icone2.png"));
        primaryStage.getIcons().add(iconImage);

        primaryStage.setWidth(util.ScreanSize.getInstance().getWidth());
        primaryStage.setHeight(util.ScreanSize.getInstance().getHeight());

        ViewSimpleFactory.createView("LOGIN");

    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}
