package com.example.proyectomarcos.pojo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String apellidos;
    private String clave;
    private String telefono;
    private String esActivo;
    private String esJunta;

    public Usuario(int id, String nombre, String apellidos, String clave, String telefono, String esActivo, String esJunta) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.clave = clave;
        this.telefono = telefono;
        this.esActivo = esActivo;
        this.esJunta = esJunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(String esActivo) {
        this.esActivo = esActivo;
    }

    public String getEsJunta() {
        return esJunta;
    }

    public void setEsJunta(String esJunta) {
        this.esJunta = esJunta;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}



