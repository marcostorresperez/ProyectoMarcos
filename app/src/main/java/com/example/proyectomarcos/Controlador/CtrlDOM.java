package com.example.proyectomarcos.Controlador;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class CtrlDOM {
    File file = new File("random.xml");

    public static Document instanciarDocumento() throws ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }

    public static Document instanciarDocumento(File XmlFile) throws ParserConfigurationException,
            IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(XmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    public static void escribirDocumentoAXML(Document doc, File file) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(file);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
    }

    public static String getValorEtiqueta(String etiqueta, Element element) {
        Node nValue = element.getElementsByTagName(etiqueta).item(0);
        return nValue.getChildNodes().item(0).getNodeValue();
    }

    public static Element getElementEtiqueta(String etiqueta, Element element) {
        return (Element) element.getElementsByTagName(etiqueta).item(0);
    }
}
