package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.proyectomarcos.R;

public class PrincipalActivity extends AppCompatActivity {

    private Button btn1,btn2,btn3,btn4,btn5,btn6;
    private Button salir;
    private TextView titulo;
    private EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

     //   Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}