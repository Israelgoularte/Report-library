package org.dev.service.usuario;

import org.dev.model.UsuarioModel;
import org.dev.model.dao.HibernateUsuarioDAO;
import org.dev.service.MyServicesDAO;
import org.dev.util.HashUtils;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

public class LoginMyServices extends MyServicesDAO {

    private final String username;

    private final String password;

    public LoginMyServices(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        try{
            String hashSenha = HashUtils.hashSenha(password);
            System.out.println(username +" "+ password + " " + hashSenha);
            UsuarioModel usuarioModel = new HibernateUsuarioDAO
                    (EntityManagerContexto.getInstance().getContexto())
                    .findByUsernameAndPassword(username,hashSenha);
            if (usuarioModel ==null){
                return new GenericMenssage<>(false,"Nenhum usuario encontrado");
            }else{
                UsuarioContexto.getInstance().setContexto(usuarioModel);
                return new GenericMenssage<>(true,"Login realizado com sucesso");
            }
        }catch (Exception e){
            //e.printStackTrace();
            return new GenericMenssage<>(false,e.getMessage());
        }
    }
}
