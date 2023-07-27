package org.dev.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.dev.model.AplicativoModel;
import org.dev.model.LinksModel;
import org.dev.view.ViewSimpleFactory;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox centralBox;

    @FXML

    private TextField nomeField;
    @FXML
    private TextArea descricaoArea;

    @FXML
    private Menu menu_Disponiveis;

    private File selectedImage;

    private File selectedIcon;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<LinksModel> listaAplicativos = carregarLinks();
        for (LinksModel link: listaAplicativos ) {
            menu_Disponiveis
                    .getItems()
                    .add(createItem_MenuDisponiveis(link));
        }
    }

    public HBox createHBox(AplicativoModel aplicativoModel) {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("vbox-background");
        hBox.setSpacing(5);
        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setMinHeight(110);

        VBox nomeBox = new VBox();
        nomeBox.setSpacing(10);
        nomeBox.setAlignment(javafx.geometry.Pos.CENTER);
        Label nomeLabel = new Label(aplicativoModel.getNome());
        nomeLabel.setMinWidth(200);
        nomeLabel.setWrapText(true);
        nomeBox.getChildren().addAll(nomeLabel);

        VBox descricaoBox = new VBox();
        descricaoBox.setSpacing(10);
        descricaoBox.setAlignment(javafx.geometry.Pos.CENTER);
        Label descricaoLabel = new Label(aplicativoModel.getDescricao());
        descricaoLabel.setMaxWidth(600);
        descricaoLabel.setWrapText(true);
        descricaoBox.getChildren().addAll(descricaoLabel);

        VBox buttonsBox = new VBox();
        buttonsBox.setSpacing(10);
        buttonsBox.setAlignment(javafx.geometry.Pos.CENTER);
        Button salvarButton = new Button("Start");
        salvarButton.setMinWidth(100);
        Button limparButton = new Button("Close");
        limparButton.setMinWidth(100);
        buttonsBox.getChildren().addAll(salvarButton, limparButton);

        hBox.getChildren().addAll(nomeBox, descricaoBox, buttonsBox);
        HBox.setMargin(nomeBox, new Insets(10));
        HBox.setMargin(descricaoBox, new Insets(10));
        HBox.setMargin(buttonsBox, new Insets(10));

        return hBox;
    }

    private List<AplicativoModel> carregarListaAplicativos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bluewolf-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Realize a consulta no banco de dados para obter a lista de aplicativos
        List<AplicativoModel> listaAplicativos = em.createQuery("SELECT a FROM AplicativoModel a", AplicativoModel.class)
                .getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return listaAplicativos;
    }

    public MenuItem createItem_MenuDisponiveis(LinksModel linksModel){
        MenuItem menuItem = new MenuItem();
        menuItem.setText(linksModel.getNome());
        menuItem.setOnAction( e ->{

        });
        return menuItem;
    }

    private List<LinksModel> carregarLinks() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bluewolf-unit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Realize a consulta no banco de dados para obter a lista de aplicativos
        List<LinksModel> listaAplicativos = em.createQuery("SELECT a FROM LinksModel a", LinksModel.class)
                .getResultList();

        em.getTransaction().commit();
        em.close();
        emf.close();

        return listaAplicativos;
    }
    @FXML
    private void dadosUsuario() {
        // Lógica para exibir os dados do usuário
        ViewSimpleFactory.createView("DADOS_CADASTRAIS");
    }

    @FXML
    private void logout() {
        UsuarioController.getInstance().logout();
    }

    public void adicionarImagem(ActionEvent actionEvent) {


        if (selectImg(selectedImage)) {
            // Faça algo com o arquivo selecionado, como carregá-lo em uma imagem ou salvá-lo em um local específico
            // Exemplo:
            System.out.println("Arquivo selecionado: " + selectedImage.getAbsolutePath());
        } else {
            // Nenhum arquivo foi selecionado
        }

    }

    public boolean selectImg(File file){
        FileChooser fileChooser = new FileChooser();

        // Defina o título da janela de seleção de arquivo
        fileChooser.setTitle("Selecione um arquivo");

        // Adicione filtros para limitar o tipo de arquivos que o usuário pode selecionar (opcional)
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Documentos PDF", "*.pdf"),
                new ExtensionFilter("Todos os arquivos", "*.*")
        );

        Stage stage = new Stage();
        file = fileChooser.showOpenDialog(stage);
        // ou
        // File selectedFile = fileChooser.showSaveDialog(stage);

        if (file != null) {
            return true;
        } else {
            return false;
        }
    }



    public void adicionarIcon(ActionEvent actionEvent) {
        if (selectImg(selectedIcon)) {
            // Faça algo com o arquivo selecionado, como carregá-lo em uma imagem ou salvá-lo em um local específico
            // Exemplo:
            System.out.println("Arquivo selecionado: " + selectedImage.getAbsolutePath());
        } else {
            // Nenhum arquivo foi selecionado
        }
    }

    public void salvar(ActionEvent actionEvent) {
    }

    public void limpar(ActionEvent actionEvent) {
        nomeField.setText("");
        descricaoArea.setText("");
        selectedImage = null;
        selectedIcon  = null;
    }
}
