package com.example.proyectomarcos.Controlador;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.proyectomarcos.pojo.Day;
import com.example.proyectomarcos.pojo.Information;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class CtrlFile extends CtrlDOM{
    private static final String ET_INFORMATION = "information";

    private static final String ET_DAY1 = "day1";
    private static final String ET_DAY2 = "day2";
    private static final String ET_DAY3 = "day3";
    private static final String ET_DAY4 = "day4";
    private static final String ET_DAY5 = "day5";


    public CtrlFile() {
    }

    public static Document recuperar(File xmlFile) throws IOException, SAXException, ParserConfigurationException, ParserConfigurationException, SAXException {
        Document doc = instanciarDocument(xmlFile);
        return doc;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<Day> read(Document doc) {

        ArrayList<Day> days = new ArrayList<Day>();
        Day day = null;

        Element arrel = doc.getDocumentElement();

        NodeList nodeList = arrel.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodeList.item(i).getNodeName().equals(ET_DAY1)) {
                    day = CtrlDay.read((Element) nodeList.item(i));
                    days.add(day);
                } else if (nodeList.item(i).getNodeName().equals(ET_DAY2)) {
                    day = CtrlDay.read((Element) nodeList.item(i));
                    days.add(day);
                } else if (nodeList.item(i).getNodeName().equals(ET_DAY3)) {
                    day = CtrlDay.read((Element) nodeList.item(i));
                    days.add(day);
                } else if (nodeList.item(i).getNodeName().equals(ET_DAY4)) {
                    day = CtrlDay.read((Element) nodeList.item(i));
                    days.add(day);
                } else if (nodeList.item(i).getNodeName().equals(ET_DAY5)) {
                    day = CtrlDay.read((Element) nodeList.item(i));
                    days.add(day);
                }
            }
        }

        return days;
    }

    public static Information info(Document doc){
        Information information = new Information();

        Element arrel = doc.getDocumentElement();

        NodeList nodeList = arrel.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodeList.item(i).getNodeName().equals(ET_INFORMATION)) {
                    information = CtrlInformation.read((Element) nodeList.item(i));
                }
            }
        }
        return information;
    }
}
