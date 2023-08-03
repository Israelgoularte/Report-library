package org.dev.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DadosCadastraisView implements ViewInterface {
    @Override
    public BorderPane createRoot() throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("/view/dadosCadastrais.fxml"));

        Tab x = new Tab();
        return root;
    }
}
