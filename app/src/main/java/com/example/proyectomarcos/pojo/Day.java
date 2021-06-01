package com.example.proyectomarcos.pojo;

import java.io.Serializable;
import java.time.LocalDate;

public class Day implements Serializable {

    private Long id;
    private LocalDate date;
    private Float temperature_max;
    private Float temperature_min;
    private String text;
    private int humidity;
    private String icon;
    private int wind;
    private String wind_direction;

    public Day(LocalDate date,
               Float temperature_max,
               Float temperature_min,
               String text,
               int humidity,
               int wind,
               String wind_direction,
               String icon) {
        this.date = date;
        this.temperature_max = temperature_max;
        this.temperature_min = temperature_min;
        this.text = text;
        this.humidity = humidity;
        this.wind = wind;
        this.wind_direction = wind_direction;
        this.icon = icon;
    }

    public Day(Long id,
               LocalDate date,
               Float temperature_max,
               Float temperature_min,
               String text,
               int humidity,
               int wind,
               String wind_direction,
               String icon) {
        this.id = id;
        this.date = date;
        this.temperature_max = temperature_max;
        this.temperature_min = temperature_min;
        this.text = text;
        this.humidity = humidity;
        this.wind = wind;
        this.wind_direction = wind_direction;
        this.icon = icon;
    }

    public Day() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getTemperature_max() {
        return temperature_max;
    }

    public void setTemperature_max(Float temperature_max) {
        this.temperature_max = temperature_max;
    }

    public Float getTemperature_min() {
        return temperature_min;
    }

    public void setTemperature_min(Float temperature_min) {
        this.temperature_min = temperature_min;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getWind() {
        return wind;
    }

    public void setWind(int wind) {
        this.wind = wind;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", date=" + date +
                ", temperature_max=" + temperature_max +
                ", temperature_min=" + temperature_min +
                ", text='" + text + '\'' +
                ", humidity=" + humidity +
                ", wind=" + wind +
                ", wind_direction='" + wind_direction +
                '}';
    }
}
