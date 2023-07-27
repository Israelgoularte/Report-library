package org.dev.control;

import jakarta.persistence.EntityManager;
import org.dev.model.LinksModel;

import java.util.List;

public class LinksManager {

    private List<LinksModel> lista = null;
    private EntityManager entityManager;

    public LinksManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<LinksModel> getLista() {
        if (lista == null) {
            // Realize a consulta no banco de dados para obter a lista de links
            lista = entityManager.createQuery("SELECT a FROM LinksModel a", LinksModel.class)
                    .getResultList();
        }
        return lista;
    }

    public boolean excluirLink(LinksModel link) {
        try {
            entityManager.getTransaction().begin();
            // Antes de remover, verifique se a entidade está gerenciada
            if (!entityManager.contains(link)) {
                link = entityManager.merge(link);
            }
            entityManager.remove(link);
            entityManager.getTransaction().commit();
            lista = null; // Define a lista como null para que seja recarregada no próximo acesso
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean adicionarLink(LinksModel link) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(link);
            entityManager.getTransaction().commit();
            lista = null; // Define a lista como null para que seja recarregada no próximo acesso
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
