package org.dev.service.usuario;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.dev.model.dao.HibernateUsuarioDAO;
import org.dev.service.MyServicesDAO;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

public class ExcluirContaMyServices extends MyServicesDAO {
    @Override
    public GenericMenssage<Boolean, String> execute() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Essa ação e inreversivel ao clicar no botão de confirmar sua conta sera excluida permanentemente!");
        alert.setTitle("Excluir Conta");
        alert.setContentText("Deseja realmente excluir sua conta?");
        alert.showAndWait();
        if(alert.getResult().equals(ButtonType.OK)){
            try{
                new HibernateUsuarioDAO(EntityManagerContexto.getInstance().getContexto())
                        .delete(UsuarioContexto.getInstance().getContexto());
                return new GenericMenssage<>(true,"Conta excluida com sucesso");
            }catch (Exception e){
                return new GenericMenssage<>(false,e.getMessage());
            }
        }
        else return new GenericMenssage<>(false,"A conta se mantem ativa");
    }
}
