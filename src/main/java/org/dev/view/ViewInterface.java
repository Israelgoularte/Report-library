package org.dev.view;

import javafx.scene.layout.BorderPane;

import java.io.IOException;

public interface ViewInterface {

    public BorderPane createRoot() throws IOException;
}
