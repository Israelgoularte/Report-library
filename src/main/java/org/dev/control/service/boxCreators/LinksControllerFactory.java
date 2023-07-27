package org.dev.control.service.boxCreators;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.dev.control.StyleControl;
import org.dev.control.service.LinksService;
import org.dev.model.LinksModel;
import org.dev.util.OpenBrowserLink;

// LinksControllerFactory.java
public class LinksControllerFactory implements LinksViewFactory {


    @Override
    public void createLinkBox(VBox centralBox) {
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link :
                service.getLista()) {
            addBox(link,service,centralBox);
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
                addBox(link,service,centralBox);
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
                addBox(link,service,centralBox);
        }
    }

    public void createLinkBoxCategoriaFiltered(String categoria,VBox centralBox) throws IllegalAccessException {
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link: service.getLista()) {
            if(link.getcategoria().toLowerCase().equals(categoria.toLowerCase()))
                addBox(link,service,centralBox);
        }
    }

    public void createLinkBoxTipeFiltered(String tipe,VBox centralBox) throws IllegalAccessException {
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link: service.getLista()) {
            if(link.getTipo().toLowerCase().equals(tipe.toLowerCase()))
                addBox(link,service,centralBox);
        }
    }

    public void createLinkBoxNomeFiltered(String nome,VBox centralBox) throws IllegalAccessException {
        LinksService service = LinksService.getInstance();
        centralBox.getChildren().clear();
        for (LinksModel link: service.getLista()) {
            if(link.getNome().toLowerCase().contains(nome.toLowerCase()))
                addBox(link,service,centralBox);
        }
    }

    
    private void addBox(LinksModel link, LinksService service, VBox centralBox){
        Background boxBackgroud = new Background(new BackgroundFill(StyleControl.getInstance().getStyle().getBackgroudColor_front(),null,null));
        Double elementsMaxHeight =  100.0;

        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(5));
        hBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        hBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(hBox,Priority.ALWAYS);



        VBox[] vBox = new VBox[4];
        for (int i =0; i<vBox.length;i++){
            vBox[i] = new VBox();
            vBox[i] .setSpacing(5);
            vBox[i] .setPadding(new Insets(5));
            vBox[i] .setBorder(new Border(new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
            vBox[i] .setAlignment(Pos.CENTER);
            HBox.setHgrow(vBox[i] ,Priority.ALWAYS);
            vBox[i].setBackground(boxBackgroud);
        }


        Label lblNome = new Label("Nome");
        TextField tfNome = new TextField(link.getNome());
        tfNome.setMinHeight(elementsMaxHeight);
        tfNome.setEditable(false);
        HBox.setHgrow(tfNome,Priority.ALWAYS);
        HBox.setHgrow(lblNome,Priority.ALWAYS);

        vBox[0].getChildren().addAll(lblNome,tfNome);

        Label lblCategoria = new Label("Categoria");
        TextField tfCategoria = new TextField(link.getcategoria());
        tfCategoria.setMinHeight(elementsMaxHeight);
        tfCategoria.setEditable(false);
        HBox.setHgrow(lblCategoria,Priority.ALWAYS);
        HBox.setHgrow(tfCategoria,Priority.ALWAYS);

        vBox[1].getChildren().addAll(lblCategoria,tfCategoria);

        Label lblDescricao = new Label("Descricao");
        TextArea taDescricao = new TextArea(link.getDescricao());
        taDescricao.setMaxHeight(elementsMaxHeight);
        taDescricao.setEditable(false);
        taDescricao.setWrapText(true);
        HBox.setHgrow(lblDescricao,Priority.ALWAYS);
        HBox.setHgrow(taDescricao,Priority.ALWAYS);

        vBox[2].getChildren().addAll(lblDescricao,taDescricao);

        double btnMinWidth = 200.0;
        Button btnDownload = new Button("Acessar");
        Button btnEditar = new Button("Editar");
        Button btnExcluir = new Button("Excluir");
        Button btnSalvarEdicao = new Button("Salvar");
        Button btnResetar = new Button("Reset");

        vBox[3].getChildren().addAll(btnDownload,btnEditar,btnExcluir);

        btnDownload.setMinWidth(btnMinWidth);
        btnEditar.setMinWidth(btnMinWidth);
        btnExcluir.setMinWidth(btnMinWidth);
        btnSalvarEdicao.setMinWidth(btnMinWidth);
        btnResetar.setMinWidth(btnMinWidth);

        btnSalvarEdicao.setOnAction(e ->{
            try {
                service.atualizarLink(link,tfNome.getText(),tfCategoria.getText(),link.getLink(),taDescricao.getText());

            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                tfNome.setText(link.getNome());
                tfCategoria.setText(link.getcategoria());
                taDescricao.setText(link.getDescricao());

            }finally {
                vBox[3].getChildren().remove(btnResetar);
                vBox[3].getChildren().remove(btnSalvarEdicao);
                vBox[3].getChildren().add(btnEditar);
                vBox[3].getChildren().add(btnExcluir);
                tfNome.setEditable(false);
                tfCategoria.setEditable(false);
                taDescricao.setEditable(false);
            }
        });

        btnResetar.setOnAction(e ->{

        });

        btnDownload.setOnAction(e ->
        {
            if(OpenBrowserLink.acessarLink(link.getLink())){
                System.out.println("Link aberto no navegador");
            }else System.out.println("Acesso negado a: " + link.getNome() + " link: " + link.getLink() );
        });

        btnEditar.setOnAction( e ->{
            tfNome.setEditable(true);
            tfCategoria.setEditable(true);
            taDescricao.setEditable(true);
            vBox[3].getChildren().remove(btnEditar);
            vBox[3].getChildren().remove(btnExcluir);
            vBox[3].getChildren().add(btnResetar);
            vBox[3].getChildren().add(btnSalvarEdicao);
        });

        btnExcluir.setOnAction(e ->{
            try {
                service.excluirLink(link);
                createLinkBox(centralBox);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        });



        hBox.getChildren().addAll(vBox);

        centralBox.getChildren().add(hBox);
    }
}

