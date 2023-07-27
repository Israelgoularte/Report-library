package org.dev.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class CadastrarNovoLinksView implements ViewInterface{
    @Override
    public BorderPane createRoot() throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("/view/cadastrarNovoLinksView.fxml"));
        return root;
    }
}
