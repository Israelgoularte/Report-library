package org.dev.control.service;

import javafx.scene.control.ChoiceBox;
import org.dev.control.UnitControl;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * A classe SelectBDService é responsável por adicionar as opções de unidades de persistência disponíveis
 * em um arquivo persistence.xml a um ChoiceBox no JavaFX, permitindo que o usuário selecione a unidade de
 * persistência desejada.
 * @author Israel Goularte
 * @version 1.0
 * @since 2023-07-23
 */
public class SelectBDService {

    /**
     * Adiciona as opções de unidades de persistência disponíveis em um arquivo persistence.xml a um ChoiceBox
     * no JavaFX.
     *
     * @param selectServer O ChoiceBox onde as opções de unidades de persistência serão adicionadas.
     * @throws RuntimeException           Se ocorrer um erro de leitura do arquivo persistence.xml ou configuração do parser XML.
     * @throws SAXException                Se ocorrer um erro de parse do arquivo XML.
     * @throws URISyntaxException         Se ocorrer um erro ao criar a URI para o arquivo persistence.xml.
     */
    public static void addDataBaseChoices(ChoiceBox<String> selectServer) {
        try {
            // Obtém os nomes das unidades de persistência do arquivo persistence.xml
            List<String> units = PersistenceController.getUnitsName();

            // Adiciona os nomes das unidades de persistência ao ChoiceBox
            for (String unit : units) {
                selectServer.getItems().add(unit);
            }

            // Adiciona um evento para quando uma opção for selecionada no ChoiceBox
            selectServer.setOnAction(e -> {
                // Define a unidade de persistência selecionada no objeto UnitControl
                UnitControl.getInstance().setUnit(selectServer.getValue());
            });
        } catch (IOException | ParserConfigurationException | SAXException | URISyntaxException e) {
            // Lança uma exceção RuntimeException se ocorrer algum erro ao ler o arquivo persistence.xml
            // ou configurar o parser XML
            throw new RuntimeException(e);
        }
    }
}
