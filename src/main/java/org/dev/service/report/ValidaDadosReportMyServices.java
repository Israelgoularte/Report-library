package org.dev.service.report;

import org.dev.service.MyServicesDAO;
import org.dev.util.menssagensInternas.GenericMenssage;

public class ValidaDadosReportMyServices extends MyServicesDAO {
    private final String nome;
    private final String tipo;
    private final String categoria;
    private final String link;
    private final String descricao;

    public ValidaDadosReportMyServices(String nome, String tipo, String categoria, String link, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.link = link;
        this.descricao = descricao;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        Boolean situacao = false;
        String menssagem;
        if (nome.equals("")){
            menssagem="Nome Obrigatorio";
        } else if (tipo.equals("")) {
            menssagem="Tipo Obrigatorio";
        } else if (categoria.equals("")) {
            menssagem="Categoria Obrigatoria";
        } else if (link.equals("")) {
            menssagem="Link Obrigatorio";
        } else if (descricao.equals("")) {
            menssagem="Descricao Obrigatoria";
        }else {
            menssagem="Dados Validos";
            situacao =true;
        }
        return new GenericMenssage<>(situacao,menssagem);
    }
}
