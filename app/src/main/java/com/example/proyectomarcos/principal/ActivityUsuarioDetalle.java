package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.fragment.Activity_Fragment_Detalle;
import com.example.proyectomarcos.pojo.Usuario;

public class ActivityUsuarioDetalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_usuario_detalle);

        Activity_Fragment_Detalle detalle=(Activity_Fragment_Detalle)getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);

        detalle.mostrarDetalle((Usuario)getIntent().getSerializableExtra("UsuarioDetalle"));

    }
}