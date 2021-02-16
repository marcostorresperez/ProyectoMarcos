package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.bd.OperacionesBD;
import com.example.proyectomarcos.bd.UsuariosSQLiteHelper;
import com.example.proyectomarcos.pojo.Usuario;

public class LoginActivity extends AppCompatActivity {
    public static final String REGEX_DNI = "^[0-9]{8,8}[A-Za-z]$";

    private EditText txtUsuario, txtContra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UsuariosSQLiteHelper dbHelper = new UsuariosSQLiteHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        txtUsuario = findViewById(R.id.txtUser);
        txtContra = findViewById(R.id.textPass);
    }


    public void sePulsa(View v) {
        if (!txtUsuario.getText().toString().isEmpty() && !txtContra.getText().toString().isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setId(Integer.parseInt(txtUsuario.getText().toString()));
            usuario.setClave(txtContra.getText().toString());
            OperacionesBD api = OperacionesBD.getInstance(getApplicationContext());
            Usuario resultado = api.login(usuario);

            if (resultado != null) {
                Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                intent.putExtra("usuario", resultado);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Datos de inicio de sesion incorrectos", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}