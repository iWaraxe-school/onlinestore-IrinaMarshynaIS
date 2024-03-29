package by.issoft.store.XML;

import by.issoft.store.XML.SortingTypes.SortCategory;
import by.issoft.store.XML.SortingTypes.SortType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {

    static final String PATH = "\"C:\\\\Users\\\\irinamarshyna\\\\IdeaProjects\\\\onlinestore-IrinaMarshynaIS\\\\store\\\\src\\\\main\\\\resources\\\\config.xml\";\n";
    public static Map<SortCategory, SortType> configMap() {
        Map<SortCategory, SortType> config = new LinkedHashMap<>();
        String filename = PATH;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Document document = null;
        try {
            db = factory.newDocumentBuilder();
            document = db.parse(new File(filename)); //копия xml документа
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        Node node = document.getElementsByTagName("sort").item(0);
        NodeList childNodes = node.getChildNodes();
        Element element;
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                element = (Element) childNodes.item(i);
                try {
                    SortCategory key = SortCategory.getCategory(element.getTagName());
                    SortType value = SortType.getCategory(element.getTextContent());
                    config.put(key, value);  // из document передаем в Map
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return config;
    }
}
