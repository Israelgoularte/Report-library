package org.dev.control.service;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class AuthenticationTask extends Task<Boolean> {

    private final String username;
    private final String password;

    private final ProgressBar progressBar;

    public AuthenticationTask(String username, String password, ProgressBar progressBar) {
        this.username = username;
        this.password = password;
        this.progressBar = progressBar;
    }

    @Override
    protected Boolean call() {
        int totalSteps = 4;
        int stepAtual = 0;
        updateProgress(stepAtual+=1, totalSteps);
        try {
            UsuarioService usi = UsuarioService.getInstance();
            updateProgress(stepAtual+=1, totalSteps);
            if (usi.login(username, password)) {
                updateProgress(stepAtual += 1, totalSteps);
                if (usi.getPessoa().getContent() != null) {
                    updateProgress(1, 1);
                    progressBar.setStyle("-fx-accent: #02f602;");
                    Thread.sleep(1000);
                    return true;
                }
            }

            Thread.sleep(1000);
        } catch (Exception e) {
            updateMessage(e.getMessage());
            updateValue(false);
            setException(e); // Define a exceção ocorrida para ser capturada no evento setOnFailed
        }
        updateProgress(1, 1); // Atualiza o progresso para 100%
        return false;
    }
}
