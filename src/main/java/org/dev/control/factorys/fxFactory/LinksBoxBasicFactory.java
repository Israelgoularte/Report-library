package org.dev.control.factorys.fxFactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.dev.control.factorys.AbstractFactory;
import org.dev.model.LinksModel;

public class LinksBoxBasicFactory implements AbstractFactory<HBox,LinksModel> {

    @Override
    public HBox create(LinksModel link) {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);

        HBox hboxNome = new HBox();
        hboxNome.setSpacing(5);
        Label lblNome = new Label("Nome: ");
        Text nome = new Text(link.getNome());

        HBox hboxDescricao = new HBox();
        hboxDescricao.setSpacing(5);
        Label lblDescricao = new Label("Descrição: ");
        Text descricao = new Text(link.getcategoria());

        HBox hboxDataCricao = new HBox();
        hboxDataCricao.setSpacing(5);
        Label lblDataCriacao = new Label("Adicionado em: ");
        Text dataCriacao = new Text(link.getDataDeCriacao().toString());

        hBox.getChildren().addAll(hboxNome,hboxDescricao,hboxDataCricao);
        return hBox;
    }
}
