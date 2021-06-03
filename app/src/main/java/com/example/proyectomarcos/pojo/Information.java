
package com.example.proyectomarcos.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Information implements Serializable {

    private String temperature;
    private String wind;
    private String humidity;
    private String pressure;

    public Information() {}

    public Information(String temperature, String wind, String humidity, String pressure) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

}
