package org.dev.util.menssagensInternas;

public interface MenssagemInterna<T,D> {
    T getMenssageOne();

    D getMenssageTwo();

    void setMenssageOne(T menssageOne);

    void setMenssageTwo(D menssageTwo);
}
