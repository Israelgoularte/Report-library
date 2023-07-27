package org.dev.control.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.dev.model.LinksModel;

import java.util.List;

public class LinksRepository extends Repository<List<LinksModel>, LinksModel> {
    private List<LinksModel> links;

    public LinksRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<LinksModel> getContent() {
        if (links == null) {
            links = entityManager.createQuery("SELECT a FROM LinksModel a", LinksModel.class)
                    .getResultList();
        }
        return links;
    }

    @Override
    public void excluirElemento(LinksModel ...elementos) {
        for (LinksModel elemento: elementos) {
            EntityTransaction ts = entityManager.getTransaction();
            try {
                ts.begin();
                LinksModel mergedElement = entityManager.merge(elemento);
                entityManager.remove(mergedElement);
                ts.commit();
                links.remove(elemento);
            } catch (Exception e) {
                ts.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void adicionarElemento(LinksModel ...elementos) {
        for (LinksModel elemento : elementos) {
            EntityTransaction ts = entityManager.getTransaction();
            try {
                ts.begin();
                entityManager.persist(elemento);
                ts.commit();
                links.add(elemento);
            } catch (Exception e) {
                ts.rollback();
                throw e;
            }
        }
    }

    @Override
    public void atualizarElemento(LinksModel ...elementos) {
        for (LinksModel elemento: elementos) {

            EntityTransaction ts = entityManager.getTransaction();
            try {
                ts.begin();
                entityManager.merge(elemento);
                ts.commit();
            } catch (Exception e) {
                ts.rollback();
                e.printStackTrace();
            }
        }
    }

    public List<String> selectDistinctInfo(String collum){
        List<String> info = entityManager.createQuery(" SELECT distinct l."+collum+" from LinksModel as l")
                .getResultList();
        return info;
    }
}
