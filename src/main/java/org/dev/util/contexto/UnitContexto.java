package org.dev.util.contexto;

public class UnitContexto extends Contexto<String>{
    private static UnitContexto instance;

    public static UnitContexto getInstance() {
        if(instance ==null){
            instance= new UnitContexto();
        }
        return instance;
    }
}
