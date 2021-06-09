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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
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
        binding = ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        btnSocios = findViewById(R.id.btnSocios);
        nombre = findViewById(R.id.correoUser);

        DrawerLayout drawer = binding.getRoot();

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.emergenciasActivity, R.id.normativaActivity, R.id.principalActivity, R.id.usuarioActivity, R.id.tiempoActivity)
                .setDrawerLayout(drawer)
                .build();
    }

}