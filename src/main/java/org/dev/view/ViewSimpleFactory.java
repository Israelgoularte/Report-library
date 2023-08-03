package org.dev.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.dev.util.contexto.StageContexto;

import java.io.IOException;

public class ViewSimpleFactory {

    public static void createView(String tela){
        ViewInterface vf = ConstantsView.valueOf(tela).getVf();
        try {
            BorderPane root = vf.createRoot();

            Stage stage = StageContexto.getInstance().getContexto();
            Scene scene = new Scene(root,stage.getWidth(),stage.getHeight());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
