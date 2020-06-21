package Presentation;

import Presentation.Model.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
  * <b>Xml est une classe qui va créer un fichier Xml et sauvegarder tous les messages de la discussion du ChatRoom.</b>
  * <p>
  * Xml contient :
  * <ul>
  * <li>Quatre paramètres configurables qui permet de lire un fichier Xml en utilisant Xpath en Java.</li>
  *</ul>
  * </p>
  */
public class Xml {

    /**
     * Les quatres parmamètres permmettant de lire un fichier Xml.
     */
    private DocumentBuilderFactory documentFactory;
    private DocumentBuilder documentBuilder;
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    
    /**
     * Constructeur de Xml.
     */
    public Xml() {
        try {
            documentFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentFactory.newDocumentBuilder();
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
    /**
     * Analyse les éléments du fichier Xml.
     * Retourne la liste de noeuds.
     * @param filePath
     *              Le chemin du fichier.
     *
     * @return la liste de noeuds.
     */
    public NodeList parseXMLFile (String filePath) {
        NodeList elementNodes = null;
        try {
            Document document= documentBuilder.parse(new File(filePath));
            Element root = document.getDocumentElement();
            elementNodes = root.getChildNodes();
        }
        catch (SAXException | IOException e) { e.printStackTrace(); }

        return elementNodes;
    }
    
    /**
     * Retourne la liste de messages.
     *
     * @return la liste de messages.
     *
     * @see Message
     */
    public List<Message> readXMLMessages() {
        NodeList nodes = this.parseXMLFile(Constants.XMLMESSAGES_INPUT_FILE);
        if (nodes == null) {
            System.out.println("error XML");
            return null;
        }
        List<Message> messagesList = new ArrayList<>();
        for (int i = 0; i<nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element currentElement = (Element) nodes.item(i);
                if (currentElement.getNodeName().equals("message")) {
                    try {
                        String name = currentElement.getElementsByTagName("name").item(0).getTextContent();
                        String text = currentElement.getElementsByTagName("text").item(0).getTextContent();
                        String date = currentElement.getElementsByTagName("date").item(0).getTextContent();
                        Message mess = new Message(name,6666,text,date);
                        messagesList.add(mess);

                    } catch (Exception ex) {
                        System.out.println("Something is wrong with the XML message element");
                    }
                }
            }
        }
        System.out.println("message charged !");
        return messagesList;
    }

    /**
     * Retourne un document de type Xml.
     *
     * @return un document de type Xml.
     */
    public Document createXMLDocument() {
        return documentBuilder.newDocument();
    }

    /**
     * Crée un fichier Xml.
     *
     * @param document
     *              Le document concerné.
     *
     * @param filePath
     *              Le chemin du fichier.
     */
    public void createXMLFile(Document document, String filePath) {
        try {
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filePath));

            transformer.transform(domSource, streamResult);

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        System.out.println("Done creating XML File");
    }

    /**
     * Ecrit les messages sur le fichier Xml.
     * 
     * @param messageList
     *              La liste des messages.
     * @see Message
     */
     public void writeXMLMessages(List<Message> messagesList) {
        Document document = this.createXMLDocument();
        if (document == null) return;

        Element message;
        Element messageName;
        Element messageText;
        Element messageDate;

        Element root = document.createElement("messages");
        document.appendChild(root);

        for (int i = 0; i < messagesList.size(); i++) {
            Message currentMessage = messagesList.get(i);
            message = document.createElement("message");

            messageName = document.createElement("name");
            messageName.appendChild(document.createTextNode(currentMessage.getSenderName()));
            message.appendChild(messageName);

            messageText = document.createElement("text");
            messageText.appendChild(document.createTextNode(currentMessage.getText()));
            message.appendChild(messageText);

            messageDate = document.createElement("date");
            messageDate.appendChild(document.createTextNode(currentMessage.getDate()));
            message.appendChild(messageDate);

            root.appendChild(message);
        }

        this.createXMLFile(document, Constants.XMLMESSAGES_OUTPUT_FILE);
    }
}
