package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.bd.UsuariosSQLiteHelper;
import com.example.proyectomarcos.pojo.Usuario;

import java.util.ArrayList;

public class SociosActivity extends AppCompatActivity {

    UsuariosSQLiteHelper api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socios);

        api= UsuariosSQLiteHelper.getInstance(this);
      //1
        // ArrayList<Usuario> listaUsuarios= api.getUsuarioDAO((Usuario) );
    }
}