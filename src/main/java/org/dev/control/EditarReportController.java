package org.dev.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.dev.model.ReportModel;
import org.dev.service.MyServicesDAO;
import org.dev.service.report.AtualizarReport;
import org.dev.service.report.ValidaDadosReportMyServices;
import org.dev.service.usuario.LogoutMyServices;
import org.dev.util.ExceptionMensagen;
import org.dev.util.TipoAndCategoriaChoiceLoad;
import org.dev.util.contexto.ReportContexto;
import org.dev.util.menssagensInternas.GenericMenssage;
import org.dev.util.tarefas.ServiceTask;
import org.dev.view.ViewSimpleFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EditarReportController implements Initializable {

    @FXML
    private TextField tfNome;

    @FXML
    private HBox boxTipo;

    @FXML
    private ChoiceBox<String> choiceBoxTipo;

    private TextField novoTipoField;

    @FXML
    private HBox boxCategoria;

    @FXML
    private ChoiceBox<String> choiceBoxCategoria;

    private TextField novaCategoriaField;

    @FXML
    private TextField tfLink;

    @FXML
    private TextArea taDescricao;

    @FXML
    private ProgressBar progress;


    @FXML
    private VBox warningBox;

    private Label warning;
    private ReportModel report;


    @FXML
    private Menu report_menu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warning = new Label();

        report = ReportContexto.getInstance().getContexto();

        tfNome.setText(report.getNome());
        tfNome.setPromptText(report.getNome());

        tfLink.setText(report.getLink());
        tfLink.setPromptText(report.getLink());

        taDescricao.setPromptText(report.getDescricao());
        taDescricao.setText(report.getDescricao());

        TipoAndCategoriaChoiceLoad.options(boxTipo, choiceBoxTipo, boxCategoria, choiceBoxCategoria, report_menu);
    }



    @FXML
    public void salvarAlteracao() {
        warningBox.getChildren().remove(warning);
        warningBox.setVisible(true);
        progress.setVisible(true);
        progress.setStyle("-fx-accent: #e3d70d;");
        DoubleProperty prog = new SimpleDoubleProperty(0.0);
        progress.progressProperty().bind(prog);

        String nome = tfNome.getText();
        String tipo;
        String categoria;
        String link = tfLink.getText();
        String descricao = taDescricao.getText();
        if(boxTipo.getChildren().contains(novoTipoField)){
            tipo = novoTipoField.getText();
        }else{
            tipo = choiceBoxTipo.getValue();
        }
        if(boxCategoria.getChildren().contains(novaCategoriaField)){
            categoria = novaCategoriaField.getText();
        }else{
            categoria = choiceBoxCategoria.getValue();
        }
        prog.setValue(0.1);

        GenericMenssage<Boolean,String> validaDadosResult = new ValidaDadosReportMyServices(nome,tipo,categoria,link,descricao).execute();

        if (validaDadosResult.getMenssageOne()){

            prog.setValue(0.2);

            MyServicesDAO serviceDAO = new AtualizarReport(report,nome,tipo,categoria,link,descricao);

            ServiceTask task = new ServiceTask(serviceDAO,progress);

            task.setOnSucceeded(e->{
                if (task.getValue()) {
                    warning.setText(task.getMessage());
                    warningBox.getChildren().add(warning);
                    voltar();
                } else {
                    progress.setStyle("-fx-accent: #ff0e13;");
                    warning.setText(ExceptionMensagen.simpleMenssage(task.getMessage()));
                    warningBox.getChildren().add(1,warning);
                }
            });

            task.setOnFailed(e->{
                progress.setStyle("-fx-accent: #ff0e13;");
                warning.setText(ExceptionMensagen.simpleMenssage(task.getMessage()));
                warningBox.getChildren().add(1,warning);
            });

            prog.setValue(0.2);
            progress.progressProperty().bind(task.progressProperty());

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        }else{
            progress.setStyle("-fx-accent: #ff0e13;");
            prog.setValue(1);
            warning.setText(validaDadosResult.getMenssageTwo());
        }

    }

    @FXML
    public void voltar() {
        ViewSimpleFactory.createView("HOME");
    }


    @FXML
    public void logout() {
        GenericMenssage<Boolean,String> logoutResult = new LogoutMyServices().execute();
        if (logoutResult.getMenssageOne()){

            ViewSimpleFactory.createView("LOGIN");
        }else{
            //tratativa de erro
        }
    }

    @FXML
    public void dadosUsuario() {
        ViewSimpleFactory.createView("DADOS_CADASTRAIS");
    }

    @FXML
    public void novoReport() {
        ViewSimpleFactory.createView("ADD_REPORT");
    }

    @FXML
    public void homePage() {
        ViewSimpleFactory.createView("HOME");
    }

}
