package org.dev.service.fxelement;

import javafx.scene.control.ChoiceBox;
import org.dev.service.MyServicesDAO;
import org.dev.util.PersistenceController;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.contexto.UnitContexto;
import org.dev.util.menssagensInternas.GenericMenssage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class DbOptionsGenerationMyServices extends MyServicesDAO {

    private final ChoiceBox<String> dbChoiceBox;

    public DbOptionsGenerationMyServices(ChoiceBox<String> dbChoiceBox) {
        this.dbChoiceBox = dbChoiceBox;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        try {
            // Obtém os nomes das unidades de persistência do arquivo persistence.xml
            List<String> units = PersistenceController.getUnitsName();

            // Adiciona os nomes das unidades de persistência ao ChoiceBox
            for (String unit : units) {
                dbChoiceBox.getItems().add(unit);
            }

            // Adiciona um evento para quando uma opção for selecionada no ChoiceBox
            dbChoiceBox.setOnAction(e -> {
                // Define a unidade de persistência selecionada no objeto UnitControl
                UnitContexto.getInstance().setContexto(dbChoiceBox.getValue());
                EntityManagerContexto.getInstance().setContexto(null);
            });
            return new GenericMenssage<>(true,"DbChoice criado com sucesso");
        } catch (IOException | ParserConfigurationException | SAXException | URISyntaxException e) {
            // Lança uma exceção RuntimeException se ocorrer algum erro ao ler o arquivo persistence.xml
            // ou configurar o parser XML
            return new GenericMenssage<>(false,e.getMessage());
        }
    }
}
