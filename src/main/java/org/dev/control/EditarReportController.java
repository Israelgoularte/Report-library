package org.dev.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.dev.control.UnitControl;
import org.dev.control.service.EditarReportTask;
import org.dev.control.service.LinksService;
import org.dev.control.service.ReportEditavel;
import org.dev.control.service.UsuarioService;
import org.dev.control.service.boxCreators.MenuNavegacaoFactory;
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


    @FXML
    private Menu links_menu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warning = new Label();

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
        tfTipo.setValue(report.getTipo());

        tfTipo.setOnAction(e->{
            novoTipoField = new TextField("");
            novoTipoField.getStyleClass().add("field-custom");
            if(tfTipo.getValue().equalsIgnoreCase("Outro")){
                choiceAction(boxTipo, tfTipo, novoTipoField);
            }
        });

        List<String> categorias = LinksService.getInstance().categoriasCadastradas();
        for (String categoria : categorias) {
            tfCategoria.getItems().add(categoria);
        }
        tfCategoria.getItems().add("Outra");

        tfCategoria.setValue(report.getcategoria());

        tfCategoria.setOnAction(e->{
            novaCategoriaField = new TextField("");
            novaCategoriaField.getStyleClass().add("field-custom");
            if(tfCategoria.getValue().equalsIgnoreCase("Outra")){
                choiceAction(boxCategoria, tfCategoria, novaCategoriaField);
            }
        });

        MenuNavegacaoFactory.createMenu(links_menu);
    }

    private void choiceAction(HBox box, ChoiceBox<String> choiceBox, TextField field) {
        box.getChildren().remove(choiceBox);
        Button btnOk = new Button("OK");
        btnOk.getStyleClass().add("button-acessar");
        btnOk.setOnAction(f-> {
            salvar(field, choiceBox, box);
        });

        Button btnCancel = new Button("Cancelar");
        btnCancel.getStyleClass().add("button-excluir");
        btnCancel.setOnAction(f->{
            cancelar(choiceBox, box);
        });

        VBox btnbox = new VBox();
        btnbox.getStyleClass().add("comum-box");
        btnbox.getChildren().addAll(btnOk,btnCancel);
        box.getChildren().addAll(field,btnbox);
    }


    @FXML
    public void salvarAlteracao() {
        warningBox.getChildren().remove(warning);
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
                warning.setText("Salvo com Sucesso");
                warningBox.getChildren().add(warning);
                voltar();
            } else {
                progress.setStyle("-fx-accent: #ff0e13;");
                System.out.println( "Sucess Menssagem: " + task.getMessage());
                warning.setText(ExceptionMensagen.simpleMenssage(task.getMessage(),"Report" ));
                warningBox.getChildren().add(1,warning);
            }
        });

        task.setOnFailed(e->{
            progress.setStyle("-fx-accent: #ff0e13;");
            System.out.println("Fail menssagem: "+task.getException().getMessage());
            warning.setText(ExceptionMensagen.simpleMenssage(task.getMessage(), "Report"));
            warningBox.getChildren().add(1,warning);
        });


        progress.progressProperty().bind(task.progressProperty());

        warningBox.setVisible(true);
        progress.setVisible(true);

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
        Node first = box.getChildren().get(0);
        box.getChildren().clear();
        box.getChildren().add(first);
    }

    @FXML
    public void logout() {
        UsuarioService.closeService();
        UnitControl.getInstance().setUnit(null);
        ViewSimpleFactory.createView("LOGIN");
    }

    @FXML
    public void dadosUsuario() {
        LinksService.closeService();
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
