package org.dev.service.usuario;

import org.dev.model.UsuarioModel;
import org.dev.model.dao.HibernateUsuarioDAO;
import org.dev.service.MyServicesDAO;
import org.dev.util.ValidaEmail;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

public class AtualizarEmailMyServices extends MyServicesDAO {
    private final String email;

    public AtualizarEmailMyServices(String email) {
        this.email = email;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        if(ValidaEmail.isValidEmail(email)){

            UsuarioModel usuarioModel = UsuarioContexto.getInstance().getContexto();
            usuarioModel.setEmail(email);
            try {
                new HibernateUsuarioDAO(EntityManagerContexto.getInstance().getContexto()).update(usuarioModel);
                return new GenericMenssage<>(true, "Cadastro atualizado com Sucesso");
            }catch (Exception e){
                return new GenericMenssage<>(false,e.getMessage());
            }
        }else return new GenericMenssage<>(false,"Insira um email valido");
    }
}
