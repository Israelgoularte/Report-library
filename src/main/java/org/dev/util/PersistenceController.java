package org.dev.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe PersistenceController é responsável por ler o arquivo persistence.xml e recuperar os nomes
 * das unidades de persistência definidas nele.
 * @author Israel Goularte
 * @version 1.0
 * @since 2023-07-23
 */
public class PersistenceController {

    /**
     * Retorna uma lista de nomes das unidades de persistência definidas no arquivo persistence.xml.
     *
     * @return Uma lista contendo os nomes das unidades de persistência.
     * @throws IOException                  Se ocorrer um erro de leitura do arquivo persistence.xml.
     * @throws ParserConfigurationException Se ocorrer um erro de configuração do parser XML.
     * @throws SAXException                 Se ocorrer um erro de parse do arquivo XML.
     * @throws URISyntaxException          Se ocorrer um erro ao criar a URI para o arquivo persistence.xml.
     */
    public static List<String> getUnitsName() throws IOException, ParserConfigurationException, SAXException, URISyntaxException {
        // Obtém o arquivo de entrada
        File inputFile = new File(new URI("" + ClassLoader.getSystemResource("META-INF/persistence.xml")));

        // Cria um DocumentBuilderFactory
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        // Cria um DocumentBuilder
        DocumentBuilder db = dbFactory.newDocumentBuilder();

        // Faz o parse do arquivo de entrada
        Document document = db.parse(inputFile);

        // Obtém o elemento raiz
        Element rootElement = document.getDocumentElement();

        // Obtém a lista de unidades de persistência
        NodeList unitsList = rootElement.getElementsByTagName("persistence-unit");

        // Cria uma lista para armazenar os nomes das unidades
        List<String> unitNames = new ArrayList<>();

        // Itera pela lista de unidades
        for (int i = 0; i < unitsList.getLength(); i++) {
            // Obtém a unidade atual
            Element unitElement = (Element) unitsList.item(i);

            // Obtém o nome da unidade
            String unitName = unitElement.getAttribute("name");

            // Adiciona o nome da unidade à lista
            unitNames.add(unitName);
        }

        // Retorna a lista de nomes das unidades
        return unitNames;
    }
}
