package org.dev.control;

import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.dev.model.LinksModel;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class LinksController implements Initializable {

    @FXML
    private VBox centralBox;

    private LinksManager linksManager;

    private List<HBox> linksBloco = new LinkedList<>();
    public void dadosUsuario(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        linksManager = new LinksManager(Persistence.createEntityManagerFactory("bluewolf-unit").createEntityManager());
        createLinksBloco();
    }



    public void createLinksBloco(){
        for (LinksModel link : linksManager.getLista()) {
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
            Text descricao = new Text(link.getTipodeprograma());

            HBox hboxDataCricao = new HBox();
            hboxDataCricao.setSpacing(5);
            Label lblDataCriacao = new Label("Adicionado em: ");
            Text dataCriacao = new Text(link.getDataDeCriacao().toString());

            HBox hboxBotoes = new HBox();
            hboxBotoes.setSpacing(5);
            Button btnDownload = new Button("Download");
            btnDownload.setOnAction(e ->{

            });

            Button btnEditar = new Button("Editar");
            btnEditar.setOnAction(e ->{

            });

            Button btnExcluir = new Button("Excluir");
            btnExcluir.setOnAction(e ->{

            });

            hBox.getChildren().addAll(hboxNome,hboxDescricao,hboxDataCricao,hboxBotoes);

            centralBox.getChildren().add(hBox);
        }
    }
}
