package org.dev.service.usuario;

import org.dev.service.MyServicesDAO;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.contexto.ReportContexto;
import org.dev.util.contexto.UnitContexto;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

public class LogoutMyServices extends MyServicesDAO {
    @Override
    public GenericMenssage<Boolean,String> execute() {
        try{
            UsuarioContexto.getInstance().setContexto(null);
            EntityManagerContexto.getInstance().getContexto().close();
            EntityManagerContexto.getInstance().setContexto(null);
            ReportContexto.getInstance().setContexto(null);
            UnitContexto.getInstance().setContexto(null);
            return new GenericMenssage<>(true,"Logout realizado com sucesso");
        }catch (Exception e){
            return new GenericMenssage<>(false, e.getMessage());
        }
    }
}
