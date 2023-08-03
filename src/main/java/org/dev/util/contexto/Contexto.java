package org.dev.util.contexto;

public abstract class Contexto<T> {

    protected T contexto;

    protected Contexto(){}

    public T getContexto() {
        return contexto;
    }

    public void setContexto(T Contexto) {
        this.contexto = Contexto;
    }
}
