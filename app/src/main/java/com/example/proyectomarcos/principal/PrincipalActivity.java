package com.example.proyectomarcos.principal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.databinding.ActivityDrawerBinding;
import com.example.proyectomarcos.pojo.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDrawerBinding binding;

    private Button btnSocios;
    private TextView nombre;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        btnSocios = findViewById(R.id.btnSocios);
        nombre = findViewById(R.id.correoUser);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.emergenciasActivity, R.id.normativaActivity, R.id.principalActivity, R.id.usuarioActivity, R.id.tiempoActivity)
                .setDrawerLayout(drawer)
                .build();
        //Creamos las instancias y variables necesarias de Firebase

    }

    //Ejemplo de uno de los botos para cambiar a cualquier otra Activity
    public void btnSocios(View v) {
        Intent intent = new Intent(PrincipalActivity.this, UsuarioActivity.class);
        startActivity(intent);
    }

    public void btnEmergencias(View v) {
        Intent intent = new Intent(PrincipalActivity.this, EmergenciasActivity.class);
        startActivity(intent);
    }

    public void btnSalir(View v) {

        onBackPressed();
        Intent intent = new Intent(PrincipalActivity.this, LoginActivityKT.class);
        startActivity(intent);
    }

    public void btnNormativa(View v) {
        Intent intent = new Intent(PrincipalActivity.this, NormativaActivity.class);
        startActivity(intent);
    }


    public void btnTiempo(View v) {
        Intent intent = new Intent(PrincipalActivity.this, TiempoActivity.class);
        startActivity(intent);
    }

    public void btnMapa(View v) {
        Intent intent = new Intent(PrincipalActivity.this, MapsActivity.class);
        startActivity(intent);
    }


}