package org.dev.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.dev.model.ReportModel;
import org.dev.model.UsuarioModel;
import org.dev.util.contexto.UsuarioContexto;

import java.util.List;

public class HibernateReportDAO extends HibernateDAO<ReportModel, Integer> implements ReportDAO {

    public HibernateReportDAO(EntityManager entityManager) {
        super(entityManager, ReportModel.class);
    }

    @Override
    public List<ReportModel> findByUser(UsuarioModel usuarioModel) {
        try {
            // Criar a consulta JPQL para buscar os relatórios associados ao loginModel
            String jpql = "SELECT r FROM ReportModel r WHERE r.usuarioId = :usuarioId";

            // Obter o EntityManager da classe pai (HibernateDAO)

            // Criar a consulta JPQL e definir o parâmetro
            TypedQuery<ReportModel> query = entityManager.createQuery(jpql, ReportModel.class);
            query.setParameter("usuarioId", usuarioModel.getIdUsuario());

            // Executar a consulta e retornar a lista de relatórios
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> distinctTipo(UsuarioModel usuarioModel) {
        try {
            // Criar a consulta JPQL para buscar os relatórios associados ao loginModel
            String jpql = "SELECT distinct r.tipo FROM ReportModel r WHERE r.usuarioId = :usuarioId";

            // Obter o EntityManager da classe pai (HibernateDAO)

            // Criar a consulta JPQL e definir o parâmetro
            TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
            query.setParameter("usuarioId", usuarioModel.getIdUsuario());

            // Executar a consulta e retornar a lista de relatórios
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> distinctCategoria(UsuarioModel usuarioModel) {
        try {
            // Criar a consulta JPQL para buscar os relatórios associados ao loginModel
            String jpql = "SELECT distinct r.categoria FROM ReportModel r WHERE r.usuarioId = :usuarioId";

            // Obter o EntityManager da classe pai (HibernateDAO)

            // Criar a consulta JPQL e definir o parâmetro
            TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
            query.setParameter("usuarioId", usuarioModel.getIdUsuario());

            // Executar a consulta e retornar a lista de relatórios
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(ReportModel reportModel){
        super.update(reportModel);
        UsuarioContexto.getInstance().getContexto().getReportsByIdUsuario().add(reportModel);
    }

    @Override
    public void delete(ReportModel reportModel){
        super.update(reportModel);
        UsuarioContexto.getInstance().getContexto().getReportsByIdUsuario().remove(reportModel);
    }
}