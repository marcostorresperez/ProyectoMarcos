package com.example.proyectomarcos.principal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.fragment.Activity_Fragment_Detalle;
import com.example.proyectomarcos.pojo.Usuario;

import java.util.ArrayList;

public class SociosDetalleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_activity_detalle);

        Activity_Fragment_Detalle detalle = (Activity_Fragment_Detalle) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        detalle.mostrarDatos((ArrayList<Usuario>) getIntent().getSerializableExtra("ListaUsuarios"));
    }
}
