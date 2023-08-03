package org.dev.util.contexto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerContexto extends Contexto<EntityManager> {
    private static EntityManagerContexto instance;

    public static EntityManagerContexto getInstance() {
        if (instance ==null){
            instance=new EntityManagerContexto();
        }
        return instance;
    }

    @Override
    public EntityManager getContexto() {
        EntityManager entityManager = super.getContexto();
        if (entityManager==null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(UnitContexto.getInstance().getContexto());
            entityManager = emf.createEntityManager();
            super.setContexto(entityManager);
        }
        return entityManager;
    }

    @Override
    public void setContexto(EntityManager entityManager) {
        if (entityManager == null) {
            try {
                super.getContexto().close();
                super.setContexto(null);
            } catch (Exception e) {
                super.setContexto(null);
            }
        }
        else super.setContexto(entityManager);
    }
}
