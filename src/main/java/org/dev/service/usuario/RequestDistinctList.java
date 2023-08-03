package org.dev.service.usuario;

import org.dev.model.UsuarioModel;
import org.dev.model.dao.HibernateReportDAO;
import org.dev.model.dao.ReportDAO;
import org.dev.service.MyServices;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

import java.util.LinkedList;
import java.util.List;

public class RequestDistinctList implements MyServices<Boolean, List<String>> {
    private final String collunName;

    public RequestDistinctList(String collunName) {
        this.collunName = collunName;
    }

    @Override
    public GenericMenssage<Boolean, List<String>> execute() {
        List<String> distinctList = new LinkedList<>();
        try{
            ReportDAO dao = new HibernateReportDAO(EntityManagerContexto.getInstance().getContexto());
            UsuarioModel usuarioModel = UsuarioContexto.getInstance().getContexto();
            if (collunName.equalsIgnoreCase("tipo")){
                distinctList = dao.distinctTipo(usuarioModel);
            } else if (collunName.equalsIgnoreCase("categoria")) {
                distinctList = dao.distinctCategoria(usuarioModel);
            }else distinctList = null;
            if (distinctList.size()!=0)
                return new GenericMenssage<>(true,distinctList);
        }catch (Exception e){
            distinctList.add(e.getMessage());
            return new GenericMenssage<>(false,distinctList);
        }
        distinctList.add("CollunName inexistente");
        return new GenericMenssage<>(false,distinctList);
    }
}
