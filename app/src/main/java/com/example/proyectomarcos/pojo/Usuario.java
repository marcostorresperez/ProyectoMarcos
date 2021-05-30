package com.example.proyectomarcos.pojo;

import com.google.firebase.firestore.GeoPoint;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private String uid;
    private String correo;
    private String nombre;
    private String apellido;
    private String telefono;
    private Boolean esJunta;
    private ArrayList<GeoPoint> puntos;

    public Usuario(String uid, String correo, String nombre, String apellido, String telefono) {
        this.uid = uid;
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.esJunta = false;
        this.puntos = new ArrayList<>();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public Boolean getEsJunta() {
        return esJunta;
    }

    public void setEsJunta(Boolean esJunta) {
        this.esJunta = esJunta;
    }

    public ArrayList<GeoPoint> getPuntos() {
        return puntos;
    }

    public void setPuntos(ArrayList<GeoPoint> puntos) {
        this.puntos = puntos;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}



