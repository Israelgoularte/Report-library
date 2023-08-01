package org.dev.control.repository;

import jakarta.persistence.EntityManager;

import java.sql.SQLException;

public abstract class  Repository<T,E> {
    protected EntityManager entityManager;

    public Repository(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    public abstract T getContent() throws IllegalAccessException;
    public abstract void excluirElemento(E ...elemento) throws IllegalAccessException;
    public abstract void adicionarElemento(E ...elemento) throws IllegalAccessException;
    public abstract boolean atualizarElemento(E ...elemento) throws Throwable;



}
