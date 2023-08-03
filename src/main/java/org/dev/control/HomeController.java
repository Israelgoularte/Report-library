package org.dev.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.dev.service.fxelement.HomeReportChoiceOptionsFilter;
import org.dev.service.fxelement.HomeReportFilterCard;
import org.dev.service.fxelement.HomeReportLoadMyServices;
import org.dev.service.fxelement.MenuReportOptionsMyServices;
import org.dev.service.usuario.LogoutMyServices;
import org.dev.util.menssagensInternas.GenericMenssage;
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
    private Menu menuReport;

    @FXML
    private ChoiceBox<String> tipeChoiceFilter;

    //INTERNOS

    public HomeController(){}

    //inicializador

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadLinksBox();

        GenericMenssage<Boolean,String> addChoiceOptionsTipoFilterResult = new HomeReportChoiceOptionsFilter(tipeChoiceFilter,"tipo").execute();
        if (addChoiceOptionsTipoFilterResult.getMenssageOne()){
            //possibilidade de tratativa de erros
        }

        GenericMenssage<Boolean,String> addChoiceOptionCategoriaFilterResult = new HomeReportChoiceOptionsFilter(categoriaChoiceFilter,"categoria").execute();
        if (addChoiceOptionCategoriaFilterResult.getMenssageOne()){
            //possibilidade de tratativa de eerros
        }

        GenericMenssage<Boolean,String> menuReportLoadResult = new MenuReportOptionsMyServices(menuReport).execute();
        if (menuReportLoadResult.getMenssageOne()){
            //possibilidade de tratativa de erros.
        }
    }

    public void loadLinksBox(){
        GenericMenssage<Boolean,String> loadCardsResult = new HomeReportLoadMyServices(centralBox).execute();
        if (loadCardsResult.getMenssageOne()){
            //possibilidade de realizar tratativa de erros;
        }
        haveContent();
    }

    //Ações
    @FXML
    public void dadosUsuario() {
        ViewSimpleFactory.createView("DADOS_CADASTRAIS");
    }

    @FXML
    public void logout() {
        GenericMenssage<Boolean,String> logoutResult = new LogoutMyServices().execute();
        if (logoutResult.getMenssageOne()){
            //possibilidade de tratativa de erro;
        }
        ViewSimpleFactory.createView("LOGIN");
    }

    private void haveContent(){
        if(centralBox.getChildren().size()==0){
            Label empity = new Label("Nenhum resultado encontrado");
            empity.getStyleClass().add("label-alerta");
            Button btnAdicioanrLink = new Button("Adicionar Link");
            btnAdicioanrLink.getStyleClass().add("button-acessar");
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
        GenericMenssage<Boolean,String> filterResult = new HomeReportFilterCard
                (
                    centralBox,
                    filtroNome.getText(),
                    tipeChoiceFilter.getValue(),
                    categoriaChoiceFilter.getValue(),
                    filtroDescricao.getText()
                )
                .execute();
        haveContent();
    }
}
