package org.dev.util.tarefas;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import org.dev.service.MyServicesDAO;
import org.dev.util.menssagensInternas.GenericMenssage;

public class ServiceTask extends Task<Boolean> {

    private final MyServicesDAO serviceDAO;

    private final ProgressBar progressBar;

    public ServiceTask(MyServicesDAO service, ProgressBar progressBar) {
        this.serviceDAO = service;
        this.progressBar = progressBar;
    }

    @Override
    protected Boolean call() {
        int totalSteps = 10;
        int stepAtual = 3;
        updateProgress(stepAtual += (totalSteps/stepAtual), totalSteps);
        GenericMenssage<Boolean,String> executeMenssagem = serviceDAO.execute();

        updateProgress(stepAtual +=(totalSteps/stepAtual), totalSteps);
        updateValue(executeMenssagem.getMenssageOne());

        updateProgress(stepAtual += (totalSteps/stepAtual), totalSteps);
        updateMessage(executeMenssagem.getMenssageTwo());

        updateProgress(1, 1);
        if(executeMenssagem.getMenssageOne()) {
            progressBar.setStyle("-fx-accent: #02f602;");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
}
