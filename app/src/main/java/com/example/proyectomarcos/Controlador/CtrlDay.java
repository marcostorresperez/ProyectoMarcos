package com.example.proyectomarcos.Controlador;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.proyectomarcos.pojo.Day;

import org.w3c.dom.Element;

import java.time.LocalDate;
import java.time.LocalTime;

public class CtrlDay extends CtrlDOM {
    private static final String ET_DATE = "date";
    private static final String ET_TEMPERATURE_MAX = "temperature_max";
    private static final String ET_TEMPERATURE_MIN = "temperature_min";
    private static final String ET_TEXT = "text";
    private static final String ET_HUMIDITY = "humidity";
    private static final String ET_WIND = "wind";
    private static final String ET_WIND_DIRECTION = "wind_direction";
    private static final String ET_ICON = "icon";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Day read(Element elemDay) {
        LocalDate date = parseDate(getValorEtiqueta(ET_DATE, elemDay));
        float temperature_max = Float.parseFloat(getValorEtiqueta(ET_TEMPERATURE_MAX, elemDay));
        float temperature_min = Float.parseFloat(getValorEtiqueta(ET_TEMPERATURE_MIN, elemDay));
        String text = getValorEtiqueta(ET_TEXT, elemDay);
        int humidity = Integer.parseInt(getValorEtiqueta(ET_HUMIDITY, elemDay));
        int wind = Integer.parseInt(getValorEtiqueta(ET_WIND, elemDay));
        String wind_direction = getValorEtiqueta(ET_WIND_DIRECTION, elemDay);
        String icon = getValorEtiqueta(ET_ICON, elemDay);
        return new Day(date, temperature_max, temperature_min, text, humidity, wind, wind_direction, icon);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static LocalDate parseDate(String date) {
        String[] array = date.split("-");
        if (array[1].length() == 1) {
            array[1] = "0" + array[1];
        }
        if (array[2].length() == 1) {
            array[2] = "0" + array[2];
        }
        return LocalDate.parse(array[0] + "-" + array[1] + "-" + array[2]);
    }
}
