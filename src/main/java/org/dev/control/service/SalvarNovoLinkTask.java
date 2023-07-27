package org.dev.control.service;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import org.dev.util.HashUtils;
import org.dev.util.ValidaEmail;
import org.dev.util.ValidaSenha;

public class NovoCadastroTask extends Task<Boolean> {

    private final String usuario;
    private final String email;
    private final String senha;
    private final String nome;
    private final String dia;

    private final String mes;

    private final String ano;

    private int totalSteps = 6;

    private int step = 0;

    private final ProgressBar progressBar;


    public NovoCadastroTask(String usuario, String email, String senha, String nome, String dia, String mes, String ano, ProgressBar progressBar) {
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.progressBar = progressBar;
    }

    @Override
    protected Boolean call() {
        try {
            // Antes de iniciar a tarefa, defina a ProgressBar como visível
            updateMessage("");
            updateProgress(step++, totalSteps);
            // Realize a validação dos dados, se necessário
            if (validaDados(usuario, email, senha, nome)) {
                updateProgress(step++, totalSteps);

                String hashSenha = HashUtils.hashSenha(senha);
                updateProgress(step++, totalSteps);

                String dataDeNascimentoText = ano + "-" + mes + "-" + dia;
                updateProgress(step++, totalSteps);

                UsuarioService us = UsuarioService.getInstance();
                updateProgress(step++, totalSteps);

                // Realize o cadastro do usuário
                if (us.cadastro(usuario, email, hashSenha, nome, dataDeNascimentoText)) {
                    // Simule um pequeno atraso para mostrar a ProgressBar de forma mais visível
                    updateProgress(1, 1);
                    progressBar.setStyle("-fx-accent: #02f602;");
                    Thread.sleep(1000);
                    return true;
                }
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

    private boolean validaDados(String usuario, String email, String senha, String nome) {
        String menssagem ="1,";
        if(usuario.equals("")) {
            menssagem += "Insira um usuario valido";
        }else if(!ValidaEmail.isValidEmail(email)) {
            menssagem += "Insira um email valido";
        }else if(!ValidaSenha.isStrongPassword(senha)) {
            menssagem += "A senha deve ter no mínimo 8 caracteres e incluir:\n" +
                    "- Pelo menos uma letra maiúscula\n" +
                    "- Pelo menos uma letra minúscula\n" +
                    "- Pelo menos um número\n" +
                    "- Pelo menos um caractere especial (!@#$%^&*()_-=[]{};':\"\\|,.<>/?)";
        }else if(nome.equals("")) {
            menssagem += "Insira um nome valido";
        }else if(dia.length()>2 || dia.equals("")) {
            menssagem += "Insira uma data valida valido";
        }else if (mes.length()>2 || mes.equals("")) {
            menssagem += "Insira uma data valida valido";

        }else if(ano.length()!=4){
            menssagem += "Insira uma data valida valido";
        }else return true;
        updateMessage(menssagem );
        return false;
    }
}
