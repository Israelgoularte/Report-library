package org.dev.control.service;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class SalvarNovoLinkTask extends Task<Boolean> {

    private final String nome;
    private final String tipo;
    private final String categoria;
    private final String link;
    private final String descricao;

    private int totalSteps = 6;

    private int step = 0;

    private final ProgressBar progressBar;


    public SalvarNovoLinkTask(String nome, String tipo, String categoria, String link, String descricao, ProgressBar progressBar) {
        this.nome = nome;
        this.tipo = tipo;
        this.categoria = categoria;
        this.link = link ;
        this.descricao = descricao;
        this.progressBar = progressBar;
    }

    @Override
    protected Boolean call() {
        try {
            // Antes de iniciar a tarefa, defina a ProgressBar como visível
            updateMessage("");
            updateProgress(step++, totalSteps);
            // Realize a validação dos dados, se necessário
            if (validaDados(nome, tipo, categoria, link,descricao)) {
                updateProgress(step++, totalSteps);
                LinksService ls = LinksService.getInstance();
                updateProgress(step++, totalSteps);
                ls.adicionarLink(nome,tipo,categoria,descricao,link);
                updateProgress(1, 1);
                progressBar.setStyle("-fx-accent: #02f602;");
                Thread.sleep(1000);
                return true;
            }else{
                updateValue(false);
            }
        }catch (Exception e) {
            updateMessage("0,"+e.getMessage());
            updateValue(false);
            setException(e); // Define a exceção ocorrida para ser capturada no evento setOnFailed
        }
        updateProgress(1, 1); // Atualiza o progresso para 100%
        return false;
    }

    private boolean validaDados(String nome, String tipo, String categoria, String link, String descricao) {
        String menssagem ="1,";
        if(nome.equals("")) {
            menssagem += "Insira um NOME valido";
        }else if(tipo.equals("") || tipo.equalsIgnoreCase("Selecione")) {
            menssagem += "Escolha um TIPO valido";
        }else if(categoria.equals("") || categoria.equalsIgnoreCase("Selecione")) {
            menssagem += "Escolha uma CATEGORIA valida";
        }else if(link.equals("")) {
            menssagem += "Insira um LINK valido";
        }else if(descricao.equals("")) {
            menssagem += "Insira uma DESCRIÇÂO valida";
        }else return true;
        updateMessage(menssagem );
        return false;
    }
}
