package com.example.proyectomarcos.pojo;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String numSocio;
    private String clave;
    private String telefono;
    private String esActivo;
    private String esJunta;


    public Usuario(String nombre, String apellidos, String numSocio, String clave, String telefono, String esActivo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numSocio = numSocio;
        this.clave = clave;
        this.telefono = telefono;
        this.esActivo = esActivo;
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

    public String getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(String numSocio) {
        this.numSocio = numSocio;
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



