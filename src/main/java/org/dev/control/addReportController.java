package org.dev.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.dev.control.service.LinksService;
import org.dev.control.service.SalvarNovoLinkTask;
import org.dev.control.service.UsuarioService;
import org.dev.control.service.boxCreators.MenuNavegacaoFactory;
import org.dev.util.ExceptionMensagen;
import org.dev.view.ViewSimpleFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class addReportController implements Initializable {


    @FXML
    private TextField nomeField;

    @FXML
    private ChoiceBox<String> tipoChoice;

    @FXML
    private ChoiceBox<String> categoriaChoice;

    @FXML
    private TextField linkField;

    @FXML
    private TextArea descricaoField;

    @FXML
    private HBox tipoBox;

    @FXML
    private HBox categoriaBox;

    private TextField novoTipoField;

    private TextField novaCategoriaField;

    @FXML
    private VBox warningBox;

    @FXML
    private ProgressBar progress;

    private Label warning;

    @FXML
    private Menu links_menu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warning = new Label();
        warning.getStyleClass().add("label-warning");

        String outraCategoria = "Nova Categoria";
        String outroTipo = "Novo Tipo";

        for (String tipo: LinksService.getInstance().tiposCadastradas()) {
            tipoChoice.getItems().add(tipo);
        }
        tipoChoice.getItems().add(outroTipo);
        tipoChoice.setValue("Selecione");

        for (String categoria : LinksService.getInstance().categoriasCadastradas()) {
            categoriaChoice.getItems().add(categoria);
        }
        categoriaChoice.getItems().add(outraCategoria);
        categoriaChoice.setValue("Selecione");

        MenuNavegacaoFactory.createMenu(links_menu);
    }
    @FXML
    public void linksPage() {
        ViewSimpleFactory.createView("HOME");
    }

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

    @FXML
    public void saveNovoLink() {
        warningBox.setVisible(true);
        progress.setVisible(true);
        warningBox.getChildren().remove(warning);

        String nome = nomeField.getText();
        String tipo = tipoChoice.getValue();
        String categoria = categoriaChoice.getValue();
        String link = linkField.getText();
        String descricao = descricaoField.getText();


        SalvarNovoLinkTask task = new SalvarNovoLinkTask(nome,tipo,categoria,link,descricao,progress);
        progress.setStyle("-fx-accent: #e3d70d;");

        task.setOnSucceeded(e ->{
            if(task.getValue()){
                warning.setText("Cadastro Realizado com Sucesso!");
                warningBox.getChildren().add(1,warning);
                limparNovoLink();
            }else{
                progress.setStyle("-fx-accent: #ff0e13;");
                String[] menssagen = task.getMessage().split(",",2);
                System.out.println(menssagen[1]);
                if(menssagen[0].equals("0")){
                    warning.setText(ExceptionMensagen.simpleMenssage(menssagen[1],"Cadastro de Link" ));
                }else warning.setText(menssagen[1]);
                warningBox.getChildren().add(1,warning);
            }
        });
        task.setOnFailed(e -> {
            progress.setStyle("-fx-accent: #ff0e13;");
            String[] menssagen = task.getMessage().split(",",1);

            if(menssagen[0].equals("0")){
                warning.setText(ExceptionMensagen.simpleMenssage(menssagen[1],"Cadastro de Link" ));
            }else warning.setText(menssagen[1]);
            warningBox.getChildren().add(1,warning);
        });

        progress.progressProperty().bind(task.progressProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true); // Define a thread como daemon para que ela não impeça o encerramento da aplicação
        thread.start();
    }

    @FXML
    public void limparNovoLink() {
        nomeField.setText("");
        linkField.setText("");
        descricaoField.setText("");

        tipoChoice.setValue("Selecione");
        if(!tipoBox.getChildren().contains(tipoChoice)){
            tipoBox.getChildren().remove(2);
            tipoBox.getChildren().remove(1);
            tipoBox.getChildren().add(tipoChoice);
        }

        categoriaChoice.setValue("Selecione");
        if (!categoriaBox.getChildren().contains(categoriaChoice)){
            categoriaBox.getChildren().remove(2);
            categoriaBox.getChildren().remove(1);
            categoriaBox.getChildren().add(categoriaChoice);
        }
    }


    public void checkTipoChoice() {
        if(tipoChoice.getValue().equals("Novo Tipo")){
            novoTipoField = new TextField();
            novoTipoField.setPrefWidth(140.0);
            Button btnCancelar = new Button("Save");
            btnCancelar.setOnAction(e->{
                String text = novoTipoField.getText();
                if(!text.equals(""))
                {
                    boolean contains = false;
                    for (String s : tipoChoice.getItems().stream().toList()) {
                        if(s.equalsIgnoreCase(text)){
                            contains = true;
                            tipoChoice.setValue(s);
                            break;
                        }
                    }
                    if(!contains){
                        tipoChoice.getItems().add(0,text);
                        tipoChoice.setValue(text);
                    }
                }else{
                    tipoChoice.setValue("Selecione");
                }
                tipoBox.getChildren().removeAll(novoTipoField,btnCancelar);
                tipoBox.getChildren().add(tipoChoice);
            });
            tipoBox.getChildren().remove(tipoChoice);
            tipoBox.getChildren().addAll(novoTipoField,btnCancelar);
        }
    }

    public void checkCategoriaChoice() {
        if(categoriaChoice.getValue().equals("Nova Categoria")){
            novaCategoriaField = new TextField();
            novaCategoriaField.setPrefWidth(140.0);
            Button btnCancelar = new Button("Save");
            btnCancelar.setOnAction(e->{
                String text = novaCategoriaField.getText();
                if(!text.equals(""))
                {
                    boolean contains = false;
                    for (String s : categoriaChoice.getItems().stream().toList()) {
                        if(s.equalsIgnoreCase(text)){
                            contains = true;
                            categoriaChoice.setValue(s);
                            break;
                        }
                    }
                    if(!contains){
                        categoriaChoice.getItems().add(0,text);
                        categoriaChoice.setValue(text);
                    }
                }else{
                    categoriaChoice.setValue("Selecione");
                }
                categoriaBox.getChildren().removeAll(novaCategoriaField,btnCancelar);
                categoriaBox.getChildren().add(categoriaChoice);
            });
            categoriaBox.getChildren().remove(categoriaChoice);
            categoriaBox.getChildren().addAll(novaCategoriaField,btnCancelar);
        }
    }


}
