package org.dev.control;

import javafx.stage.Stage;

public class StageController {
    private Stage stage;

    private static StageController instance;

    private StageController(){}

    public static StageController getInstance(){
        if (instance == null){
            instance = new StageController();
        }
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
