package org.dev.service.report;

import org.dev.model.ReportModel;
import org.dev.model.dao.HibernateReportDAO;
import org.dev.service.MyServicesDAO;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

public class CadastrarNovoReport extends MyServicesDAO {
    private final String nome;

    private final String tipo;

    private final String categoria;

    private final String descricao;

    private final String link;

    public CadastrarNovoReport(String nome, String tipo, String categoria, String descricao, String link) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.link = link;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        ReportModel reportModel = new ReportModel();
        reportModel.setNome(nome);
        reportModel.setTipo(tipo);
        reportModel.setCategoria(categoria);
        reportModel.setDescricao(descricao);
        reportModel.setLink(link);
        reportModel.setUsuarioId(UsuarioContexto.getInstance().getContexto().getIdUsuario());

        try{
            new HibernateReportDAO(EntityManagerContexto.getInstance().getContexto()).save(reportModel);
            return new GenericMenssage<>(true, "Report adicionado com sucesso.");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }
    }
}
