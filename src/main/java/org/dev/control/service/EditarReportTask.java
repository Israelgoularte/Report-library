package org.dev.control.service;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import org.dev.model.LinksModel;

public class EditarReportTask extends Task<Boolean> {

    private  String nome;
    private  String tipo;
    private  String categoria;
    private  String link;
    private  String descricao;

    private int totalSteps = 3;

    private int step = 0;

    private final ProgressBar progressBar;

    private LinksModel report;


    public EditarReportTask(LinksModel report, String nome, String tipo, String categoria, String link, String descricao, ProgressBar progressBar) {
        this.report= report;
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.link = link;
        this.descricao = descricao;
        this.progressBar = progressBar;
    }

    @Override
    protected Boolean call() {
        try {
            // Antes de iniciar a tarefa, defina a ProgressBar como visível
            updateMessage("");
            updateProgress(step++, totalSteps);

            validaDados();
            updateProgress(step++, totalSteps);

            LinksService.getInstance().atualizarLink(report,nome,tipo,categoria,link,descricao);
            updateProgress(1, 1);
            progressBar.setStyle("-fx-accent: #02f602;");
            Thread.sleep(1000);
            return true;
        }catch (Exception e) {
            updateMessage(e.getMessage());
            updateValue(false);
            setException(e); // Define a exceção ocorrida para ser capturada no evento setOnFailed
            updateProgress(1, 1); // Atualiza o progresso para 100%
            return false;
        }
    }

    private void validaDados() {
        String menssagem ="";
        if(nome.equals("")) {
            nome = report.getNome();
        }else if(tipo.equals("")) {
            tipo=report.getTipo();
        }else if(categoria.equals("")) {
            categoria=report.getcategoria();
        }else if(link.equals("")) {
            link=report.getLink();
        }else if(descricao.equals("")) {
            descricao = report.getDescricao();
        }
    }
}
