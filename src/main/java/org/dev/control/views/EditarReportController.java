package org.dev.control.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.dev.control.service.EditarReportTask;
import org.dev.control.service.LinksService;
import org.dev.control.service.ReportEditavel;
import org.dev.model.LinksModel;
import org.dev.util.ExceptionMensagen;
import org.dev.view.ViewSimpleFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditarReportController implements Initializable {

    @FXML
    private TextField tfNome;

    @FXML
    private HBox boxTipo;

    @FXML
    private ChoiceBox<String> tfTipo;

    private TextField novoTipoField;

    @FXML
    private HBox boxCategoria;

    @FXML
    private ChoiceBox<String> tfCategoria;

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
    private LinksModel report;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        report = ReportEditavel.getInstance().getReport();
        tfNome.setText(report.getNome());
        tfNome.setPromptText(report.getNome());
        tfLink.setText(report.getLink());
        tfLink.setPromptText(report.getLink());
        taDescricao.setPromptText(report.getDescricao());
        taDescricao.setText(report.getDescricao());
        List<String> tipos = LinksService.getInstance().tiposCadastradas();
        for (String tipo: tipos) {
            tfTipo.getItems().add(tipo);
        }
        tfTipo.getItems().add("Outro");
        tfTipo.setOnAction(e->{
            novoTipoField = new TextField("");
            if(tfTipo.getValue().equalsIgnoreCase("Outro")){
                choiceAction(boxTipo, tfTipo, novoTipoField);
            }
        });

        List<String> categorias = LinksService.getInstance().categoriasCadastradas();
        for (String categoria : categorias) {
            tfCategoria.getItems().add(categoria);
        }
        tfCategoria.getItems().add("Outra");
        tfCategoria.setOnAction(e->{
            novaCategoriaField = new TextField("");
            if(tfCategoria.getValue().equalsIgnoreCase("Outra")){
                choiceAction(boxCategoria, tfCategoria, novaCategoriaField);
            }
        });
    }

    private void choiceAction(HBox box, ChoiceBox<String> choiceBox, TextField field) {
        box.getChildren().remove(choiceBox);
        Button btnOk = new Button("OK");
        btnOk.setOnAction(f-> {
            salvar(field, choiceBox, box);
        });

        Button btnCancel = new Button("Cancelar");
        btnCancel.setOnAction(f->{
            cancelar(choiceBox, box);
        });
        box.getChildren().addAll(field,btnOk,btnCancel);
    }


    @FXML
    public void salvarAlteracao() {
        String nome = tfNome.getText();
        String tipo;
        String categoria;
        String link = tfLink.getText();
        String descricao = taDescricao.getText();
        if(boxTipo.getChildren().contains(novoTipoField)){
            tipo = novoTipoField.getText();
        }else{
            tipo = tfTipo.getValue();
        }
        if(boxCategoria.getChildren().contains(novaCategoriaField)){
            categoria = novaCategoriaField.getText();
        }else{
            categoria = tfCategoria.getValue();
        }
        EditarReportTask task = new EditarReportTask(report,nome,tipo,categoria,link,descricao,progress);

        task.setOnSucceeded(e->{
            if (task.getValue()) {
                voltar();
            } else {
                progress.setStyle("-fx-accent: #ff0e13;");
                System.out.println("a");
                warning.setText(ExceptionMensagen.simpleMenssage(task.getException().getMessage(),"Report" ));
                warningBox.getChildren().add(1,warning);
            }
        });

        task.setOnFailed(e->{
            progress.setStyle("-fx-accent: #ff0e13;");
            warning.setText(ExceptionMensagen.simpleMenssage(task.getException().getMessage(), "Report"));
            warningBox.getChildren().add(1,warning);
        });

        progress.progressProperty().bind(task.progressProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }

    @FXML
    public void voltar() {
        ViewSimpleFactory.createView("HOME");
    }

    private void salvar(TextField field, ChoiceBox<String> choiceBox, HBox box){
        if(field.getText().equals("")){
            cancelar(choiceBox,box);
        }else{
            choiceBox.getItems().add(field.getText());
            choiceBox.setValue(field.getText());
            removerAddChoiceElement(box);
            box.getChildren().add(choiceBox);
        }
    }

    private void cancelar(ChoiceBox<String> choiceBox, HBox box){
        choiceBox.setValue("");
        removerAddChoiceElement(box);
        box.getChildren().add(choiceBox);
    }

    private void removerAddChoiceElement(HBox box){
        for (int i = 1; i < box.getChildren().size(); i++) {
            box.getChildren().remove(i);
        }
    }
}
