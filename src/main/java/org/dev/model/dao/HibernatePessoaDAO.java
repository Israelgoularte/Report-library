package org.dev.model.dao;

import jakarta.persistence.EntityManager;
import org.dev.model.PessoaModel;

public class HibernatePessoaDAO extends HibernateDAO<PessoaModel,Integer> implements PessoaDAO{
    public HibernatePessoaDAO(EntityManager entityManager) {
        super(entityManager, PessoaModel.class);
    }
}
