package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Usuario;

public class LoginActivity extends AppCompatActivity {
    public static final String REGEX_DNI = "^[0-9]{8,8}[A-Za-z]$";

    private EditText txtUsuario, txtContra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario=findViewById(R.id.txtUser);
        txtContra=findViewById(R.id.textPass);
    }


    public void sePulsa(View v) {
        if (!txtUsuario.getText().toString().isEmpty() && !txtContra.getText().toString().isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setNumSocio(txtUsuario.getText().toString());
            usuario.setClave(txtContra.getText().toString());


        }else{
            Toast.makeText(getApplicationContext(),"Rellena todos los campos",Toast.LENGTH_SHORT).show();
        }
    }
}