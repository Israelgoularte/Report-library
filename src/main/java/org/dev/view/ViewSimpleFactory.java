package org.dev.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.dev.control.StageController;

import java.io.IOException;

public class ViewSimpleFactory {

    public static void createView(String tela){
        ViewInterface vf = ConstantsView.valueOf(tela).getVf();
        try {
            BorderPane root = vf.createRoot();
            // Carregue a imagem corretamente usando getResourceAsStream
            Image img = new Image(ViewSimpleFactory.class.getResourceAsStream("/img/fundo2.png"));
            BackgroundImage backgroundImage = new BackgroundImage(
                    img,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1.0, 1.0, true, true, false, false)
            );
            root.setBackground(new Background(backgroundImage));

            Scene scene = new Scene(root);
            Stage stage = StageController.getInstance().getStage();
            stage.setScene(scene);
            //stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
