package org.dev.service.fxelement;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.dev.model.UsuarioModel;
import org.dev.model.dao.HibernateReportDAO;
import org.dev.model.dao.ReportDAO;
import org.dev.service.MyServicesDAO;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

import java.util.List;

public class ReportChoiceOptions extends MyServicesDAO {
    private final ChoiceBox<String> choiceBox;

    private final String collumName;

    private final HBox box;

    public ReportChoiceOptions(HBox box,ChoiceBox<String> choiceBox, String collumName) {
        this.choiceBox = choiceBox;
        this.collumName = collumName;
        this.box = box;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        try{

            List<String> elementos = requestList();
            for (String elemento: elementos) {
                choiceBox.getItems().add(elemento);
            }
            choiceBox.getItems().add("Outro");

            choiceBox.setOnAction(e->{
                if(choiceBox.getValue().equalsIgnoreCase("Outro")){
                    TextField novoTipoField = new TextField("");
                    novoTipoField.getStyleClass().add("text-field-menor");
                    choiceAction(box, choiceBox, novoTipoField);
                }
            });
            return new GenericMenssage<>(true, "Elementos adicionados com sucesso");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }
    }

    private void choiceAction(HBox box, ChoiceBox<String> choiceBox, TextField field) {
        box.getChildren().remove(choiceBox);
        Button btnOk = new Button("OK");
        btnOk.getStyleClass().add("button-acessar");
        btnOk.setOnAction(f-> {
            salvar(field, choiceBox, box);
        });

        Button btnCancel = new Button("Cancelar");
        btnCancel.getStyleClass().add("button-excluir");
        btnCancel.setOnAction(f->{
            cancelar(choiceBox, box);
        });

        VBox btnbox = new VBox();
        btnbox.getStyleClass().add("comum-box");
        btnbox.getChildren().addAll(btnOk,btnCancel);
        box.getChildren().addAll(field,btnbox);
    }

    private List<String> requestList(){
        ReportDAO dao = new HibernateReportDAO(EntityManagerContexto.getInstance().getContexto());
        UsuarioModel usuarioModel = UsuarioContexto.getInstance().getContexto();
        if (collumName.equalsIgnoreCase("tipo")){
            return dao.distinctTipo(usuarioModel);
        } else if (collumName.equalsIgnoreCase("categoria")) {
            return dao.distinctCategoria(usuarioModel);
        }else return null;
    }

    private void salvar(TextField field, ChoiceBox<String> choiceBox, HBox box){
        String newOption = field.getText();
        if(newOption.equals("")){
            cancelar(choiceBox,box);
        }else{
            boolean contains = false;
            for (String option:choiceBox.getItems() ) {
                if(option.equalsIgnoreCase(newOption)){
                    contains = true;
                    newOption =option;
                    break;
                }
            }
            if(!contains){
                choiceBox.getItems().add(newOption);
            }
            choiceBox.setValue(newOption);
            removeChoiceBody(box);
            box.getChildren().add(choiceBox);
        }
    }

    private void cancelar(ChoiceBox<String> choiceBox, HBox box){
        choiceBox.setValue("");
        removeChoiceBody(box);
        box.getChildren().add(choiceBox);
    }

    private void removeChoiceBody(HBox box){
        Node first = box.getChildren().get(0);
        box.getChildren().clear();
        box.getChildren().add(first);
    }
}
