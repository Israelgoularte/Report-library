package org.dev.control.factorys;

public interface AbstractFactory <T,E>{
    public T createBasic(E element);

    public T createNormal(E element);

    public T createGreat(E element);

}
