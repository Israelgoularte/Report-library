package org.dev.util.contexto;

import javafx.stage.Stage;

public class StageContexto extends Contexto<Stage>{

    private static StageContexto instance;

    public static StageContexto getInstance() {
        if(instance==null){
            instance= new StageContexto();
        }
        return instance;
    }
}
