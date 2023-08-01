package org.dev.control.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.dev.model.LoginModel;

/**
 * Classe que representa um repositório para operações relacionadas a dados de login de usuários.
 *
 * <p>Esta classe implementa a interface genérica {@link Repository} e define operações específicas
 * para manipulação de dados de login de usuários no banco de dados utilizando o framework JPA.</p>
 */
public class UserRepository extends Repository<LoginModel,String> {
    private LoginModel user; // O usuário atualmente logado.

    public UserRepository(EntityManager entityManager){
        super(entityManager);
    }


    @Override
    public LoginModel getContent() throws IllegalAccessException {
        if (user == null) {
            throw new IllegalAccessException("Acesso Negado");
        }
        return user;
    }

    @Override
    public void excluirElemento(String ...info) throws IllegalAccessException {
        //Independente do parametro Info, ira excluir o LoginModel (user) do banco de dados que esta logado.
        //caso não estiver logado ira retornar uma Exception
        if(user!=null){
            EntityTransaction ts = entityManager.getTransaction();
            try {
                ts.begin();
                LoginModel mergedElement = entityManager.merge(user);
                entityManager.remove(mergedElement);
                ts.commit();
                user = null;
            } catch (Exception e) {
                ts.rollback();
                e.printStackTrace();
            }
        }else throw new IllegalAccessException("Acesso Negado");
    }

    @Override
    public void adicionarElemento(String ...info) throws IllegalAccessException {
        if(info!=null && info.length==3)
        {
            LoginModel newUser = new LoginModel();
            newUser.setUsuario(info[0]);
            newUser.setEmail(info[1]);
            newUser.setHashsenha(info[2]);

            EntityTransaction ts = entityManager.getTransaction();
            try {
                ts.begin();
                entityManager.persist(newUser);
                ts.commit();
                this.user = newUser;
            } catch (Exception e) {
                ts.rollback();
                e.printStackTrace();
            }
        }else throw new IllegalAccessException("Acesso Negado"); //

    }

    @Override
    public boolean atualizarElemento(String ...info) throws IllegalAccessException {
        if(user == null && info!=null){ // se ainda não tiver realizado login armazena os dados do login realizado.
            user = entityManager.createQuery("SELECT l FROM LoginModel as l WHERE l.usuario = :username and l.hashsenha = :password", LoginModel.class)
                    .setParameter("username", info[0])
                    .setParameter("password", info[1])
                    .getSingleResult();
        }
        else if( user !=null &&  info == null){ // caso o elemento informado seja null, realiza logout;
            this.user = null;
        }
        else if(user !=null && info !=null && info.length==3){ // se estiver logado realiza atualização dos dados
            EntityTransaction ts = entityManager.getTransaction();
            String[] rollbackinfo = {user.getUsuario(),user.getEmail(),user.getHashsenha()};
            try {
                user.setUsuario(info[0]);
                user.setEmail(info[1]);
                user.setEmail(info[2]);
                ts.begin();
                entityManager.merge(user);
                ts.commit();
            } catch (Exception e) {
                ts.rollback();
                user.setUsuario(rollbackinfo[0]);
                user.setEmail(rollbackinfo[1]);
                user.setHashsenha(rollbackinfo[2]);
                e.printStackTrace();
            }
        }
        else throw new IllegalAccessException("Acesso Negado"); //
        return true;
    }
}
