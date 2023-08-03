package org.dev.util.contexto;

import org.dev.model.UsuarioModel;

public class UsuarioContexto extends org.dev.util.contexto.Contexto<UsuarioModel> {
    private static UsuarioContexto instance;

    public static UsuarioContexto getInstance() {
        if(instance==null){
            instance = new UsuarioContexto();
        }
        return instance;
    }
}
