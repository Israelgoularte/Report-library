package org.dev.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.dev.control.UnitControl;
import org.dev.control.service.LinksService;
import org.dev.control.service.UsuarioService;
import org.dev.control.service.boxCreators.LinksControllerFactory;
import org.dev.control.service.boxCreators.MenuNavegacaoFactory;
import org.dev.view.ViewSimpleFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {
    //Dados
    //FXML

    @FXML
    private VBox centralBox;


    @FXML
    private TextField filtroDescricao;


    @FXML
    private ChoiceBox<String> categoriaChoiceFilter;

    @FXML
    private TextField filtroNome;


    @FXML
    private Menu menu_links;

    @FXML
    private ChoiceBox<String> tipeChoiceFilter;

    //INTERNOS
    private LinksControllerFactory lcf;

    public HomeController(){
        lcf = new LinksControllerFactory();
    }

    //inicializador

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadLinksBox();
        for (String string :LinksService.getInstance().categoriasCadastradas()) {
            categoriaChoiceFilter.getItems().add(string);
        }
        categoriaChoiceFilter.getItems().add("Todos");
        categoriaChoiceFilter.setValue("Todos");
        for (String tipo: LinksService.getInstance().tiposCadastradas()) {
            this.tipeChoiceFilter.getItems().add(tipo);
        }
        tipeChoiceFilter.getItems().add("Todos");
        tipeChoiceFilter.setValue("Todos");

        MenuNavegacaoFactory.createMenu(menu_links);
    }

    public void loadLinksBox(){
        lcf.createLinkBox(centralBox);
        haveContent();
    }

    //Ações
    @FXML
    public void dadosUsuario() {
        LinksService.closeService();
        ViewSimpleFactory.createView("DADOS_CADASTRAIS");
    }

    @FXML
    public void logout() {

        UsuarioService.closeService();
        UnitControl.getInstance().setUnit(null);
        ViewSimpleFactory.createView("LOGIN");

    }

    private void haveContent(){
        if(centralBox.getChildren().size()==0){
            Label empity = new Label("Nenhum resultado encontrado");
            empity.getStyleClass().add("label-alerta");
            Button btnAdicioanrLink = new Button("Adicionar Link");
            btnAdicioanrLink.setOnAction(e ->{
                adicionarLink();
            });
            centralBox.getChildren().addAll(empity,btnAdicioanrLink);
        }
    }

    @FXML
    public void adicionarLink() {
        ViewSimpleFactory.createView("ADD_REPORT");
    }


    @FXML
    public void filter(){
        lcf.createLinkBoxFiltered(
                filtroNome.getText(),
                tipeChoiceFilter.getValue(),
                categoriaChoiceFilter.getValue(),
                filtroDescricao.getText(),
                centralBox
        );
        haveContent();
    }

    @FXML
    public void filtrarCategoria() {
        try {
            lcf.createLinkBoxCategoriaFiltered(categoriaChoiceFilter.getValue(),centralBox);
            haveContent();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void filtrarDescricao() {
        try {
            lcf.createLinkBoxDescricaoFiltered(filtroDescricao.getText(),centralBox);
            haveContent();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void filtrarNome() {
        try {
            lcf.createLinkBoxNomeFiltered(filtroNome.getText(),centralBox);
            haveContent();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void filterTipe() {
        try {
            lcf.createLinkBoxCategoriaFiltered(tipeChoiceFilter.getValue(),centralBox);
            haveContent();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
