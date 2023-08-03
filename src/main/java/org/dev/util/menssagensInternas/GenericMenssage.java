package org.dev.util.menssagensInternas;

public class GenericMenssage<T,D> implements MenssagemInterna<T,D> {
    private T menssageOne;
    private D menssageTwo;

    public GenericMenssage(T menssageOne,D menssageTwo){
        this.menssageOne = menssageOne;
        this.menssageTwo = menssageTwo;
    }

    @Override
    public T getMenssageOne() {
        return menssageOne;
    }

    @Override
    public D getMenssageTwo() {
        return menssageTwo;
    }

    @Override
    public void setMenssageOne(T menssageOne) {
        this.menssageOne =menssageOne;
    }

    @Override
    public void setMenssageTwo(D menssageTwo) {
        this.menssageTwo = menssageTwo;
    }
}
