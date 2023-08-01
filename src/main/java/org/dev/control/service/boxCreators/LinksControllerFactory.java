package org.dev.control.service.boxCreators;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.dev.control.service.LinksService;
import org.dev.control.service.ReportEditavel;
import org.dev.model.LinksModel;
import org.dev.util.ClipboardCopy;
import org.dev.util.OpenBrowserLink;
import org.dev.view.ViewSimpleFactory;

// LinksControllerFactory.java
public class LinksControllerFactory {


    public void createLinkBox(VBox centralBox) {
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link :
                service.getLista()) {
            addCard(link,service,centralBox);
        }

    }

    public void createLinkBoxFiltered(String nome, String tipo, String categoria, String descricao, VBox centralBox){
        String[] valoresValido = valoresValidos(nome,tipo,categoria,descricao);
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link: service.getLista()) {
            boolean valido = link.getNome().toLowerCase().contains(valoresValido[0].toLowerCase())
                    && link.getTipo().toLowerCase().contains(valoresValido[1].toLowerCase())
                    && link.getcategoria().toLowerCase().contains(valoresValido[2].toLowerCase())
                    && link.getDescricao().toLowerCase().contains(valoresValido[3].toLowerCase());
            if(valido){
                addCard(link,service,centralBox);
            }
        }
    }

    private String[] valoresValidos(String ... valores){
        for (int i = 0; i <valores.length; i++) {
            boolean validar = valores[i] == null
                    || valores[i].equalsIgnoreCase("todos");

            if (validar){
                valores[i]="";
            }
        }
        return valores;
    }

    public void createLinkBoxDescricaoFiltered(String descricao,VBox centralBox) throws IllegalAccessException {
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link: service.getLista()) {
            if(link.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
                addCard(link,service,centralBox);
        }
    }

    public void createLinkBoxCategoriaFiltered(String categoria,VBox centralBox) throws IllegalAccessException {
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link: service.getLista()) {
            if(link.getcategoria().toLowerCase().equals(categoria.toLowerCase()))
                addCard(link,service,centralBox);
        }
    }

    public void createLinkBoxTipeFiltered(String tipe,VBox centralBox) throws IllegalAccessException {
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link: service.getLista()) {
            if(link.getTipo().toLowerCase().equals(tipe.toLowerCase()))
                addCard(link,service,centralBox);
        }
    }

    public void createLinkBoxNomeFiltered(String nome,VBox centralBox) throws IllegalAccessException {
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link: service.getLista()) {
            if(link.getNome().toLowerCase().contains(nome.toLowerCase()))
                addCard(link,service,centralBox);
        }
    }

    private void addCard(LinksModel link, LinksService service, VBox centralBox){
        TitledPane titledPane = new TitledPane();
        titledPane.getStyleClass().add("titled-pane");
        titledPane.setExpanded(false);
        HBox.setHgrow(titledPane,Priority.ALWAYS);

        Label lblNome = new Label(link.getNome());
        Label lblCategoria = new Label(link.getcategoria());
        Label lblTipo = new Label(link.getTipo());

        //adiciono funcao de copiar conteudo com botao direito do mouse
        configLabel(lblNome,lblCategoria,lblTipo);

        HBox.setHgrow(lblNome,Priority.ALWAYS);

        lblNome.getStyleClass().add("card-label-title");

        Button acessar = new Button("Acessar");

        acessar.setOnAction(e->
        {
            OpenBrowserLink.acessarLink(link.getLink());
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

        Text textDescricao = new Text(link.getDescricao());
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
            ReportEditavel.getInstance().setReport(link);
            ViewSimpleFactory.createView("EDITAR_REPORT");
        });



        topBox.getChildren().add(btnEditar);

        HBox bottomBox = new HBox();
        bottomBox.getStyleClass().add("card-bottom-box");

        Button btnExcluir = new Button("Excluir");
        btnExcluir.getStyleClass().add("card-button-excluir");

        btnExcluir.setOnAction(e ->{
            try {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION,"Ao clicar Ok, o registro sera excluido permanentemente!", ButtonType.OK,ButtonType.NO);
                alerta.setTitle("Atenção!!!");
                alerta.setHeaderText("Deseja realmente excluir o Registro?");
                alerta.showAndWait();
                if (alerta.getResult().getText().equalsIgnoreCase("OK")){
                    service.excluirLink(link);
                    createLinkBox(centralBox);
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
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

        centralBox.getChildren().add(titledPane);
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

