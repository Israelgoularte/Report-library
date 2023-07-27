package org.dev.util;

public class ExceptionMensagen {

    public static String simpleMenssage(String menssage, String sujeito){
        if(menssage ==null){
            return "Null error";
        }
        else if(menssage.toLowerCase().contains("no result found")){
            return "Nenhum "+ sujeito +" Encontrado";
        }
        else if (menssage.toLowerCase().contains("No name provided".toLowerCase())){
            return "Escolha um banco de dados";
        }
        else if(menssage.toLowerCase().contains("A tentativa de conexão falhou".toLowerCase())){
            return "Erro de Conexão";
        }else if(menssage.toLowerCase().contains("duplicate key".toLowerCase())){
            String key = menssage.split("Detalhe: Key",2)[1];
            key = key.replace("(","");
            key = key.replace(")","");
            key = key.replace("=", ": ");
            key = key.replace("already exists", "Em uso");
            key = key.split("]",2)[0];
            return key.toUpperCase();
        }
        return "Error";
    }
}
