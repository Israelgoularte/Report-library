package org.dev.control.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import org.dev.control.UnitControl;
import org.dev.control.repository.PessoaRepository;
import org.dev.control.repository.UserRepository;
import org.dev.util.HashUtils;

import java.sql.SQLException;

public class UsuarioService {
    private UserRepository user;
    private PessoaRepository pessoa;

    private static EntityManager entityManager;

    private static UsuarioService instance;

    private UsuarioService(EntityManager entityManager){
        user = new UserRepository(entityManager);
        pessoa = new PessoaRepository(entityManager);
    }

    public static UsuarioService getInstance(){
        if(instance ==  null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(UnitControl.getInstance().getUnit());
            EntityManager em = emf.createEntityManager();
            entityManager = em;
            instance = new UsuarioService(entityManager);
        }

        return instance;
    }

    public static void closeService(){
        try{
            entityManager.close();
        }catch (NullPointerException e){

        }
        instance =null;
    }

    private void carregarPessoa() throws NoResultException {
        try {
            pessoa.atualizarElemento(String.valueOf(user.getContent().getIdLogin()));
        } catch (IllegalAccessException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean login(String usuario, String senha) throws NoResultException, SQLException {
        senha = HashUtils.hashSenha(senha);
        try {
            this.user.atualizarElemento(usuario,senha);
            carregarPessoa();
            return true;
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void logout() throws IllegalAccessException {
        this.user.atualizarElemento(null);
        this.pessoa.atualizarElemento(null);
    }

    public boolean cadastro(String usuario, String email, String hashSenha, String nome, String dataNascimento)
            throws IllegalAccessException
    {
        this.user.adicionarElemento(usuario,email,hashSenha);
        this.pessoa.adicionarElemento(String.valueOf(user.getContent().getIdLogin()),nome,dataNascimento);
        logout();
        return true;
    }
    public UserRepository getUser(){
        return this.user;
    }

    public PessoaRepository getPessoa(){
        return this.pessoa;
    }



}
