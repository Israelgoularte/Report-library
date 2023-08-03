package org.dev.service.fxelement;

import javafx.scene.control.ChoiceBox;
import org.dev.service.MyServicesDAO;
import org.dev.service.usuario.RequestDistinctList;
import org.dev.util.menssagensInternas.GenericMenssage;

import java.util.List;

public class HomeReportChoiceOptionsFilter extends MyServicesDAO {
    private final ChoiceBox<String> choiceBox;

    private final String collumName;

    public HomeReportChoiceOptionsFilter(ChoiceBox<String> choiceBox, String collumName) {
        this.choiceBox = choiceBox;
        this.collumName = collumName;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        try{
            List<String> elementos =  requestList();
            if (elementos!=null){
                for (String string : elementos) {
                    choiceBox.getItems().add(string);
                }
            }
            choiceBox.getItems().add("Todos");
            choiceBox.setValue("Todos");
            return new GenericMenssage<>(true,"Adicionado todos os itens disponiveis a o box");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }
    }

    private List<String> requestList(){
        GenericMenssage<Boolean,List<String>> distinctListResult = new RequestDistinctList(collumName).execute();
        if (distinctListResult.getMenssageOne()){
            return distinctListResult.getMenssageTwo();
        }
        else {
            //possivel tratativa de erro sendo que o primeiro elemento da lista resultante e o motivo do erro.
            return null;
        }
    }
}
