package org.dev.util;

public class MensagemInterna {
    private String menssagem;
    private boolean status;

    public MensagemInterna(String menssagem,boolean status){
        this.menssagem= menssagem;
        this.status=status;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public boolean isStatus() {
        return status;
    }
}
