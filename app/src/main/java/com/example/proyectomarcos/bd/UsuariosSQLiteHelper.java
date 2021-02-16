package com.example.proyectomarcos.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectomarcos.dao.UsuarioDAO;
import com.example.proyectomarcos.pojo.Usuario;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    private static SQLiteDatabase db;
    private static int version = 2;
    private static String nombreBD = "UsuariosDB";
    private static SQLiteDatabase.CursorFactory factory = null;


    public UsuariosSQLiteHelper(Context context) {
        super(context, nombreBD, factory, version);
    }

    private static UsuariosSQLiteHelper instance = null;
    private static UsuarioDAO usuarioDAO;

    public static UsuariosSQLiteHelper getInstance(Context context) {
        if (instance == null) {
            instance = new UsuariosSQLiteHelper(context);
            db = instance.getWritableDatabase();
            usuarioDAO = new UsuarioDAO();
        }
        return instance;
    }

    public static SQLiteDatabase getDB() {
        return db;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionTablaUsuarios);
        db.execSQL(sqlIndiceNombreUsuarios);

        for (int i = 0; i < sqlInsertaUsuarios.length; i++) {
            db.execSQL(sqlInsertaUsuarios[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String sqlCreacionTablaUsuarios = "CREATE TABLE usuarios(" +
            " _id INTEGER PRIMARY KEY," +
            " nombre TEXT NOT NULL, " +
            " apellidos TEXT NOT NULL, " +
            " telefono TEXT, " +
            " clave TEXT," +
            " esActivo BIT," +
            " esJunta BIT)";

    private String sqlIndiceNombreUsuarios = "CREATE UNIQUE INDEX nombreUsuarios ON usuarios(nombre ASC)";

    private String[] sqlInsertaUsuarios = {"INSERT INTO usuarios(_id, nombre,apellidos,telefono,clave,esActivo,esJunta) VALUES(1,'Paco','Pérez','123456789','0000',1,0)",
            "INSERT INTO usuarios(_id, nombre,apellidos,telefono,clave,esActivo,esJunta) VALUES(2,'Marcos','Torres','234567891','0000',1,1)",
            "INSERT INTO usuarios(_id, nombre,apellidos,telefono,clave,esActivo,esJunta) VALUES(3,'Jaime','García','345678912','0000',0,0)",
            "INSERT INTO usuarios(_id, nombre,apellidos,telefono,clave,esActivo,esJunta) VALUES(4,'Pepe','Sanchéz','456789123','0000',1,0)"};


}
