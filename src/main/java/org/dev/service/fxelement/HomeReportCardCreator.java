package org.dev.service.fxelement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.dev.model.ReportModel;
import org.dev.service.report.RemoverReport;
import org.dev.util.ClipboardCopy;
import org.dev.util.OpenBrowserLink;
import org.dev.util.contexto.ReportContexto;
import org.dev.util.menssagensInternas.GenericMenssage;
import org.dev.view.ViewSimpleFactory;

public class HomeReportCardCreator {

    public void execute(ReportModel reportModel, VBox homeReportBox) {
        TitledPane titledPane = new TitledPane();
        titledPane.getStyleClass().add("titled-pane");
        titledPane.setExpanded(false);
        HBox.setHgrow(titledPane, Priority.ALWAYS);

        Label lblNome = new Label(reportModel.getNome());
        Label lblCategoria = new Label(reportModel.getCategoria());
        Label lblTipo = new Label(reportModel.getTipo());

        //adiciono funcao de copiar conteudo com botao direito do mouse
        configLabel(lblNome,lblCategoria,lblTipo);

        HBox.setHgrow(lblNome,Priority.ALWAYS);

        lblNome.getStyleClass().add("card-label-title");

        Button acessar = new Button("Acessar");

        acessar.setOnAction(e->
        {
            OpenBrowserLink.acessarLink(reportModel.getLink());
        });

        acessar.getStyleClass().add("card-button-acessar");

        HBox.setHgrow(acessar,Priority.NEVER);

        HBox leftBox = new HBox();
        leftBox.getStyleClass().add("card-left");
        leftBox.getChildren().addAll(lblNome);

        HBox midBox = new HBox();
        midBox.getStyleClass().add("card-mid");
        midBox.getChildren().addAll(lblTipo,lblCategoria);

        HBox rightBox = new HBox();
        rightBox.getStyleClass().add("card-right");
        rightBox.getChildren().add(acessar);

        HBox titleBox = new HBox();
        titleBox.getStyleClass().add("card-title-box");
        titleBox.getChildren().addAll(leftBox,midBox,rightBox);


        HBox.setHgrow(leftBox,Priority.SOMETIMES);
        HBox.setHgrow(midBox,Priority.ALWAYS);
        HBox.setHgrow(rightBox,Priority.SOMETIMES);
        HBox.setHgrow(titleBox,Priority.ALWAYS);
        titledPane.setGraphic(titleBox);


        BorderPane content = new BorderPane();
        titledPane.setContent(content);

        Text textDescricao = new Text(reportModel.getDescricao());
        textDescricao.setWrappingWidth(400); //adiciona quebra de linha automatica

        textDescricao.setOnMouseClicked(e->
        {
            if (e.getButton().name().equalsIgnoreCase("SECONDARY")) {
                String text = textDescricao.getText();
                ClipboardCopy.copyToClipboard(text);
            }
        });
        textDescricao.getStyleClass().add("card-descricao");

        HBox centerBox = new HBox();
        centerBox.getStyleClass().add("card-center-box");
        centerBox.getChildren().add(textDescricao);


        HBox topBox = new HBox();
        topBox.getStyleClass().add("card-top-box");

        Button btnEditar = new Button("Editar");
        btnEditar.getStyleClass().add("card-button-editar");

        btnEditar.setOnAction(e ->{
            ReportContexto.getInstance().setContexto(reportModel);
            ViewSimpleFactory.createView("EDITAR_REPORT");
        });



        topBox.getChildren().add(btnEditar);

        HBox bottomBox = new HBox();
        bottomBox.getStyleClass().add("card-bottom-box");

        Button btnExcluir = new Button("Excluir");
        btnExcluir.getStyleClass().add("card-button-excluir");

        btnExcluir.setOnAction(e ->{
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,"Ao clicar Ok, o registro sera excluido permanentemente!", ButtonType.OK,ButtonType.NO);
                alerta.setTitle("Atenção!!!");
                alerta.setHeaderText("Deseja realmente excluir o Registro?");
                alerta.showAndWait();
                if (alerta.getResult().getText().equalsIgnoreCase("OK")){
                    GenericMenssage<Boolean,String> excluirReportResult = new RemoverReport(reportModel).execute();
                    if (excluirReportResult.getMenssageOne()){
                        new HomeReportLoadMyServices(homeReportBox).execute();
                    }
                }
            });

        bottomBox.getChildren().add(btnExcluir);

        content.setBottom(bottomBox);
        content.setTop(topBox);
        content.setCenter(centerBox);

        titledPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double newWidth = (double) newValue;
                // Ajusta o tamanho do título para 80% da largura da janela
                double titleWidth = newWidth * 0.95;
                titleBox.setMinWidth(titleWidth);
                titleBox.setMaxWidth(titleWidth);
            }
        });
        homeReportBox.getChildren().add(titledPane);
    }
    private void configLabel(Label ...labels){
        for (int i = 0; i < labels.length; i++) {
            String text = labels[i].getText();
            labels[i].setOnMouseClicked(e ->{
                if(e.getButton().name().equalsIgnoreCase("SECONDARY"))
                {
                    String textToCopy = text;
                    ClipboardCopy.copyToClipboard(textToCopy);
                }
            });
            HBox.setHgrow(labels[i],Priority.SOMETIMES);
        }
    }
}
