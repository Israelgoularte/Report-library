    package org.dev.model.dao;

    import jakarta.persistence.EntityManager;
    import jakarta.persistence.NoResultException;
    import jakarta.persistence.TypedQuery;
    import org.dev.model.PessoaModel;
    import org.dev.model.UsuarioModel;

    public class HibernateUsuarioDAO extends HibernateDAO<UsuarioModel, Integer> implements UsuarioDAO {

        public HibernateUsuarioDAO(EntityManager entityManager) {
            super(entityManager, UsuarioModel.class);
        }

        @Override
        public UsuarioModel findByUsernameAndPassword(String username, String password) {
            try {
                // Criar a consulta JPQL para buscar o usuário pelo nome de usuário (username)
                String jpql = "SELECT l FROM UsuarioModel l WHERE l.usuario = :username and l.hashsenha = :password";

                // Criar a consulta JPQL e definir o parâmetro
                TypedQuery<UsuarioModel> query = entityManager.createQuery(jpql, UsuarioModel.class);
                query.setParameter("username", username);
                query.setParameter("password", password);

                // Executar a consulta e retornar o usuário encontrado ou null se não encontrado
                return query.getSingleResult();
            } catch (NoResultException e) {
                // Retorna null caso não encontre nenhum usuário com o username fornecido
                return null;
            } catch (Exception e) {
                // Tratar outras exceções, se necessário
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public PessoaModel getPessoaByLogin(UsuarioModel usuarioModel) {
            try {
                // Obtém a pessoa associada ao LoginModel usando o atributo pessoaByPessoaId
                return usuarioModel.getPessoaByPessoaId();
            } catch (Exception e) {
                // Tratar exceções, se necessário
                e.printStackTrace();
                return null;
            }
        }
    }