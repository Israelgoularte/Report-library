package org.dev.control;

import org.dev.control.repository.Repository;
import org.dev.model.LoginModel;
import org.dev.model.PessoaModel;

public class UsuarioService {
    private Repository user;
    private PessoaModel pessoaModel;

    private static UsuarioService instance;

    private UsuarioService(){
    }

    public static UsuarioService getInstance(){
        if(instance ==  null){
            instance = new UsuarioService();
        }
        return instance;
    }

    private void carregarPessoa() {

    }

    public boolean login(String usuario, String senha){
        LoginModel login = new LoginModel();
        login.setUsuario(usuario);
        login.setHashsenha(senha);
        try {
            this.user.atualizarElemento(login);
            carregarPessoa();
            return true;
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Repository<LoginModel,LoginModel> getUser(){
        return this.user;
    }

    public PessoaModel getPessoaModel(){
        return this.pessoaModel;
    }



}
