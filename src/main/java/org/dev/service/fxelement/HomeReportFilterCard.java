package org.dev.service.fxelement;

import javafx.scene.layout.VBox;
import org.dev.model.ReportModel;
import org.dev.service.MyServicesDAO;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

public class HomeReportFilterCard extends MyServicesDAO {
    private final VBox homeReportBox;

    private final String nome;

    private final String tipo;

    private final String categoria;

    private final String descricao;

    public HomeReportFilterCard(VBox homeReportBox, String nome, String tipo, String categoria, String descricao) {
        this.homeReportBox = homeReportBox;
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        try{
            String[] valoresValido = valoresValidos(nome,tipo,categoria,descricao);
            HomeReportCardCreator cardCreator = new HomeReportCardCreator();
            homeReportBox.getChildren().clear();
            for (ReportModel reportModel: UsuarioContexto.getInstance().getContexto().getReportsByIdUsuario()) {

                boolean valido = reportModel.getNome().toLowerCase().contains(valoresValido[0].toLowerCase())
                        && reportModel.getTipo().toLowerCase().contains(valoresValido[1].toLowerCase())
                        && reportModel.getCategoria().toLowerCase().contains(valoresValido[2].toLowerCase())
                        && reportModel.getDescricao().toLowerCase().contains(valoresValido[3].toLowerCase());

                if(valido){
                    cardCreator.execute(reportModel,homeReportBox);
                }
            }
            return new GenericMenssage<>(true,"Filtro realizado com sucesso");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }
    }

    private String[] valoresValidos(String ... valores){
        for (int i = 0; i <valores.length; i++) {
            boolean validar = valores[i] == null
                    || valores[i].equalsIgnoreCase("todos");

            if (validar){
                valores[i]="";
            }
        }
        return valores;
    }
}
