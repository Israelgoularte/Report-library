package org.dev.control.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.dev.control.service.UsuarioService;
import org.dev.view.ViewSimpleFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarNovoLinksController implements Initializable {

    @FXML
    private Menu menu_navegacao;

    @FXML
    private VBox cadastrarLinkWarningbox;

    @FXML
    private TextField nomeField;

    @FXML
    private ChoiceBox<String> categoriaChoice;

    @FXML
    private TextField linkField;

    @FXML
    private TextArea textArea;

    @FXML
    private HBox selecaoCategoriaBox;

    private Label warning;

    public CadastrarNovoLinksController(){
        warning = new Label();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void dadosUsuario() {
    }

    @FXML
    public void logout() {
        try {
            UsuarioService.getInstance().logout();
            ViewSimpleFactory.createView("LOGIN");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveNovoLink() {
//        try{
//            String nome = this.nomeField.getText();
//            String categoria;

//            if(selecaoCategoriaBox.getChildren().contains(novaCategoriaField)){
//                categoria = novaCategoriaField.getText();
//            }else{
//                categoria = categoriaChoice.getValue();
//            }
//            String link = linkField.getText();
//            String descricao = textArea.getText();
//
//            boolean nomeValido = !nome.equals("");
//            boolean categoriaValida = !categoria.equals("");
//            boolean linkValido = !link.equals("");
//            boolean descricaoValida = !descricao.equals("");
//
//            boolean dadosValidos = nomeValido && categoriaValida && linkValido && descricaoValida;
//
//            if(dadosValidos){
//
//                linksService.adicionarLink(nome,categoria,descricao,link);
//                loadLinksBox();
//            }else{
//                limparNovoLink();
//                warning.setText("Insira os dados corretos");
//                warning.setTextFill(Color.RED);
//                cadastrarLinkWarningbox.getChildren().add(warning);
//                cadastrarLinkWarningbox.setMinWidth(600);
//            }
//        }catch (IllegalAccessException e) {
//            System.out.printf(e.getMessage());
//            e.printStackTrace();
//        }catch (NullPointerException e){
//            selecaoCategoriaBox.getChildren().remove(categoriaChoice);
//            selecaoCategoriaBox.getChildren().add(novaCategoriaField);
//        }
    }

    @FXML
    public void limparNovoLink() {
    }


}
