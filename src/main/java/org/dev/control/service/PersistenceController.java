package org.dev.control.service;

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

public class PersistenceController {

    public static List<String> getUnits() throws IOException, ParserConfigurationException, SAXException, URISyntaxException {
        // Get the input file
        File inputFile = new File(new URI(""+ClassLoader.getSystemResource("META-INF/persistence.xml")));

        // Create a DocumentBuilderFactory
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        // Create a DocumentBuilder
        DocumentBuilder db = dbFactory.newDocumentBuilder();

        // Parse the input file
        Document document = db.parse(inputFile);

        // Get the root element
        Element rootElement = document.getDocumentElement();

        // Get the list of units
        NodeList unitsList = rootElement.getElementsByTagName("persistence-unit");

        // Create a list to store the unit names
        List<String> unitNames = new ArrayList<>();

        // Iterate through the list of units
        for (int i = 0; i < unitsList.getLength(); i++) {
            // Get the current unit
            Element unitElement = (Element) unitsList.item(i);

            // Get the unit name
            String unitName = unitElement.getAttribute("name");

            // Add the unit name to the list
            unitNames.add(unitName);
        }

        // Print the list of unit names
        return unitNames;
    }
}
