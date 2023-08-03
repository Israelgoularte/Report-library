package org.dev.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.dev.service.MyServicesDAO;
import org.dev.service.report.CadastrarNovoReport;
import org.dev.service.report.ValidaDadosReportMyServices;
import org.dev.service.usuario.LogoutMyServices;
import org.dev.util.TipoAndCategoriaChoiceLoad;
import org.dev.util.menssagensInternas.GenericMenssage;
import org.dev.util.tarefas.ServiceTask;
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
    private Menu reportMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warning = new Label();
        warning.getStyleClass().add("label-warning");

        TipoAndCategoriaChoiceLoad.options(tipoBox, tipoChoice, categoriaBox, categoriaChoice, reportMenu);
    }

    

    @FXML
    public void linksPage() {
        ViewSimpleFactory.createView("HOME");
    }

    @FXML
    public void dadosUsuario() {
        ViewSimpleFactory.createView("DADOS_CADASTRAIS");
    }

    @FXML
    public void logout() {
        GenericMenssage<Boolean,String> logoutResult= new LogoutMyServices().execute();
        if (logoutResult.getMenssageOne()){
            ViewSimpleFactory.createView("LOGIN");
        }else{
            //tratativa de erro
        }
    }

    @FXML
    public void saveNovoLink() {
        warningBox.setVisible(true);
        progress.setVisible(true);
        warningBox.getChildren().remove(warning);
        progress.setStyle("-fx-accent: #e3d70d;");
        DoubleProperty prog = new SimpleDoubleProperty(0.0);
        progress.progressProperty().bind(prog);

        String nome = nomeField.getText();
        String tipo = tipoChoice.getValue();
        String categoria = categoriaChoice.getValue();
        String link = linkField.getText();
        String descricao = descricaoField.getText();

        prog.setValue(0.1);

        GenericMenssage<Boolean,String> validaDadosResult = new ValidaDadosReportMyServices
                (nome,tipo,categoria,link,descricao)
                .execute();
        if (validaDadosResult.getMenssageOne()){
            prog.setValue(0.2);
            
            MyServicesDAO servicesDAO = new CadastrarNovoReport(nome,tipo,categoria,descricao,link);
            ServiceTask task = new ServiceTask(servicesDAO,progress);


            task.setOnSucceeded(e ->{
                if(task.getValue()){
                    warning.setText(task.getMessage());
                    warningBox.getChildren().add(1,warning);
                    limparNovoLink();
                }else{
                    progress.setStyle("-fx-accent: #ff0e13;");
                    warning.setText(task.getMessage());
                    warningBox.getChildren().add(1,warning);
                }
            });
            task.setOnFailed(e -> {
                progress.setStyle("-fx-accent: #ff0e13;");
                warning.setText(task.getMessage());
                warningBox.getChildren().add(1,warning);
            });

            prog.setValue(0.3);
            progress.progressProperty().bind(task.progressProperty());

            Thread thread = new Thread(task);
            thread.setDaemon(true); // Define a thread como daemon para que ela não impeça o encerramento da aplicação
            thread.start();
        }else{
            prog.setValue(1);
            progress.setStyle("-fx-accent: #ff0e13;");
            warning.setText(validaDadosResult.getMenssageTwo());
            warningBox.getChildren().add(warning);
        }
        
    }

    @FXML
    public void limparNovoLink() {
        nomeField.setText("");
        linkField.setText("");
        descricaoField.setText("");

        tipoChoice.setValue("Selecione");
        if (!tipoBox.getChildren().contains(tipoChoice)) {
            tipoBox.getChildren().remove(2);
            tipoBox.getChildren().remove(1);
            tipoBox.getChildren().add(tipoChoice);
        }

        categoriaChoice.setValue("Selecione");
        if (!categoriaBox.getChildren().contains(categoriaChoice)) {
            categoriaBox.getChildren().remove(2);
            categoriaBox.getChildren().remove(1);
            categoriaBox.getChildren().add(categoriaChoice);
        }
    }
}
