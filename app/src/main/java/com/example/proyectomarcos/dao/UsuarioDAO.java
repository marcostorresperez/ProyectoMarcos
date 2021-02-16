package com.example.proyectomarcos.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.hardware.usb.UsbInterface;
import android.text.TextUtils;

import com.example.proyectomarcos.bd.UsuariosSQLiteHelper;
import com.example.proyectomarcos.pojo.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsuarioDAO implements PojoDAO {

    @Override
    public long add(Object obj) {
        ContentValues contentValues = new ContentValues();
        Usuario u = (Usuario) obj;
        contentValues.put("_id",u.getId());
        contentValues.put("nombre", u.getNombre());
        contentValues.put("apellidos", u.getApellidos());
        contentValues.put("telefono", u.getTelefono());
        contentValues.put("clave", u.getClave());
        contentValues.put("esActivo", u.getEsActivo());
        contentValues.put("esJunta", u.getEsJunta());
        return UsuariosSQLiteHelper.getDB().insert("usuarios", null, contentValues);
    }

    @Override
    public int update(Object obj) {
        ContentValues contentValues = new ContentValues();
        Usuario u = (Usuario) obj;
        contentValues.put("_id",u.getId());
        contentValues.put("nombre", u.getNombre());
        contentValues.put("apellidos", u.getApellidos());
        contentValues.put("telefono", u.getTelefono());
        contentValues.put("clave", u.getClave());
        contentValues.put("esActivo", u.getEsActivo());
        contentValues.put("esJunta", u.getEsJunta());

        String condicion = "_id=" + u.getId();

        int resultado = UsuariosSQLiteHelper.getDB().update("usuarios", contentValues, condicion, null);
        return resultado;
    }

    @Override
    public void delete(Object obj) {
        Usuario u = (Usuario) obj;
        String condicion = "_id=" + u.getTelefono();
        UsuariosSQLiteHelper.getDB().delete("usuarios", condicion, null);
    }

    @Override
    public Object search(Object obj) {
        Usuario u = (Usuario) obj;
        String condicion = "_id=" + u.getId();

        String[] columnas = {
                "_id", "nombre", "apellidos", "telefono", "clave", "esActivo", "esJunta"};

        Cursor cursor = UsuariosSQLiteHelper.getDB().query("usuarios", columnas, condicion, null, null, null, null);
        Usuario nuevoUsuario = null;
        if (cursor.moveToFirst()) {
            nuevoUsuario = new Usuario();
            nuevoUsuario.setId(cursor.getInt(0));
            nuevoUsuario.setNombre(cursor.getString(1));
            nuevoUsuario.setApellidos(cursor.getString(2));
            nuevoUsuario.setTelefono(cursor.getString(3));
            nuevoUsuario.setClave(cursor.getString(4));
            nuevoUsuario.setEsActivo(cursor.getString(5));
            nuevoUsuario.setEsJunta(cursor.getString(6));
        }
        return nuevoUsuario;
    }


    @Override
    public ArrayList getAll() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String[] columnas = {"_id", "nombre", "apellidos", "telefono", "clave", "esActivo", "esJunta"};
        Cursor cursor = UsuariosSQLiteHelper.getDB().query("usuarios", columnas, null, null, null, null, null);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (cursor.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(cursor.getInt(0));
                u.setNombre(cursor.getString(1));
                u.setApellidos(cursor.getString(2));
                u.setTelefono(cursor.getString(3));
                u.setClave(cursor.getString(4));
                u.setEsActivo(cursor.getString(5));
                u.setEsJunta(cursor.getString(6));
                listaUsuarios.add(u);
            } while (cursor.moveToNext());
        }
        return listaUsuarios;
    }
}
