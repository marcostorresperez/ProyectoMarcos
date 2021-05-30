package com.example.proyectomarcos.Controlador;


import com.example.proyectomarcos.pojo.Information;

import org.w3c.dom.Element;

public class CtrlInformation extends CtrlDOM {
    private static final String ET_TEMPERATURE = "temperature";
    private static final String ET_WIND = "wind";
    private static final String ET_HUMIDITY = "humidity";
    private static final String ET_PRESSURE = "pressure";

    public static Information read(Element elemInformation) {
        String temperature = getValorEtiqueta(ET_TEMPERATURE, elemInformation);
        String wind = getValorEtiqueta(ET_WIND, elemInformation);
        String humidity = getValorEtiqueta(ET_HUMIDITY, elemInformation);
        String pressure = getValorEtiqueta(ET_PRESSURE, elemInformation);

        return new Information(temperature, wind, humidity, pressure);
    }
}
