package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Usuario;

public class PrincipalActivity extends AppCompatActivity {

    private Button btn1, btnSocios, btnMapa, btn4, btn5, btnEmergencias;
    private Button btnsalir;
    private TextView socio;
    private TextView nombre;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //   Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        nombre = findViewById(R.id.nombre);
        nombre.setText(usuario.getNombre());
        socio = findViewById(R.id.txtSocio);
        socio.setText(String.valueOf(usuario.getId()));
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    public void btnSocios(View v) {
        Intent intent = new Intent(PrincipalActivity.this, SociosActivity.class);
        startActivity(intent);
    }

    public void btnEmergencias(View v) {
        Intent intent = new Intent(PrincipalActivity.this, EmergenciasActivity.class);
        startActivity(intent);
    }

    public void btnSalir(View v) {
        Intent intent = new Intent(PrincipalActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void btnMapa(View v) {
        Intent intent = new Intent(PrincipalActivity.this, MapsActivity.class);
        startActivity(intent);
    }

}