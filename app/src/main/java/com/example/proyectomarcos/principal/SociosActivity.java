package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.bd.UsuariosSQLiteHelper;
import com.example.proyectomarcos.fragment.Activity_Fragment_Lista;
import com.example.proyectomarcos.fragment.SociosListener;
import com.example.proyectomarcos.pojo.Usuario;

import java.util.ArrayList;

public class SociosActivity extends AppCompatActivity implements SociosListener {
    UsuariosSQLiteHelper api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socios);

        api = UsuariosSQLiteHelper.getInstance(this);
        ArrayList<Usuario> listaSocios = api.getUsuarioDAO().getAll();

        Activity_Fragment_Lista usuario = (Activity_Fragment_Lista) getSupportFragmentManager().findFragmentById(R.id.FrgListado);
        usuario.setUsuariosListener((SociosListener) this);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Usuarios", listaSocios);
        usuario.setArguments(bundle);

    }

    @Override
    public void onUsuarioSeleccionado(Usuario usuario) {

    }
}