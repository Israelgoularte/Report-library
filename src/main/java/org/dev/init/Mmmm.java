package org.dev.init;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.dev.control.StageController;
import org.dev.view.ViewSimpleFactory;

import java.io.IOException;


public class Mmmm extends Application {
            @Override
            public void start(Stage primaryStage) throws IOException, InterruptedException {
                StageController.getInstance().setStage(primaryStage);

                primaryStage.setTitle("Software WareHouse");
                Image iconImage = new Image(getClass().getResourceAsStream("/view/css/img/icone2.png"));
                primaryStage.getIcons().add(iconImage);

                primaryStage.setWidth(util.ScreanSize.getInstance().getWidth());
                primaryStage.setHeight(util.ScreanSize.getInstance().getHeight());

                ViewSimpleFactory.createView("DADOS_CADASTRAIS");

            }

            public static void main(String[] args) throws IOException {
                launch();
            }


    }

