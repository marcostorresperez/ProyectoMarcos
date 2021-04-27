package com.example.proyectomarcos.bd;

import android.content.Context;

import com.example.proyectomarcos.pojo.Usuario;

import java.util.ArrayList;

public class OperacionesBD {

    private UsuariosSQLiteHelper miBD;

    protected OperacionesBD(Context context) {
        miBD = UsuariosSQLiteHelper.getInstance(context);
    }

    private static OperacionesBD instance = null;

    public static OperacionesBD getInstance(Context context) {
        if (instance == null) {
            instance = new OperacionesBD(context);
        }
        return instance;
    }


    public Usuario login(Usuario u) {
        Usuario aux = (Usuario) miBD.getUsuarioDAO().search(u);
        if (aux == null) {
            return null;
        } else if (aux.getClave().equalsIgnoreCase(u.getClave())) {
            return aux;
        } else {
            return null;
        }
    }
}
