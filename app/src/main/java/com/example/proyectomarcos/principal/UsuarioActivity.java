package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.fragment.Activity_Fragment_Detalle;
import com.example.proyectomarcos.fragment.Activity_Fragment_Lista;
import com.example.proyectomarcos.fragment.UsuarioListener;
import com.example.proyectomarcos.pojo.Usuario;

public class UsuarioActivity extends AppCompatActivity implements UsuarioListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_usuario);

    Activity_Fragment_Lista frgListado = new Activity_Fragment_Lista();
        frgListado.setUsuarioListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.FrgListado, frgListado)
                .commit();
    }

    @Override
    public void onUsuarioSeleccionado(Usuario u) {
        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);

        if (hayDetalle) {
            ((Activity_Fragment_Detalle) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle)).mostrarDetalle(u);
        } else {
            Intent i = new Intent(this, ActivityUsuarioDetalle.class);
            i.putExtra("UsuarioDetalle", u);
            startActivity(i);
        }
    }
}