package org.dev.service.report;

import org.dev.model.ReportModel;
import org.dev.model.dao.HibernateReportDAO;
import org.dev.service.MyServicesDAO;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

public class AtualizarReport extends MyServicesDAO {
    private final String nome;

    private final String tipo;

    private final String categoria;

    private final String descricao;

    private final String link;

    private final ReportModel reportModel;

    public AtualizarReport(ReportModel reportModel,String nome, String tipo, String categoria, String descricao, String link) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.link = link;
        this.reportModel =reportModel;
    }


    @Override
    public GenericMenssage<Boolean, String> execute() {
        reportModel.setNome(nome);
        reportModel.setTipo(tipo);
        reportModel.setCategoria(categoria);
        reportModel.setDescricao(descricao);
        reportModel.setLink(link);

        try{
            new HibernateReportDAO(EntityManagerContexto.getInstance().getContexto()).update(reportModel);
            return new GenericMenssage<>(true, "Report atualizado com Sucesso");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }
    }
}
