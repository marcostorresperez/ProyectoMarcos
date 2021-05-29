package com.example.proyectomarcos.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import org.jetbrains.annotations.NotNull;

public class PrincipalActivity extends AppCompatActivity {

    private Button btn1, btnSocios, btnMapa, btn4, btnNormativa, btnEmergencias;
    private Button btnsalir;
    private TextView socio;
    private TextView nombre;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        FirebaseUser fbUser = (FirebaseUser) FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("usuarios").document(fbUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    usuario = document.toObject(Usuario.class);
                    nombre = findViewById(R.id.correoUser);
                    nombre.setText("Bienvenido \n"+ usuario.getNombre().toUpperCase());
                }
            }
        });

    }


    public void btnSocios(View v) {
        Intent intent = new Intent(PrincipalActivity.this, UsuarioActivity.class);
        startActivity(intent);
    }

    public void btnEmergencias(View v) {
        Intent intent = new Intent(PrincipalActivity.this, EmergenciasActivity.class);
        startActivity(intent);
    }

    public void btnSalir(View v) {
        Intent intent = new Intent(PrincipalActivity.this, LoginActivityKT.class);
        startActivity(intent);
    }

    public void btnNormativa(View v) {
        Intent intent = new Intent(PrincipalActivity.this, NormativaActivity.class);
        startActivity(intent);
    }

    public void btnMapa(View v) {
        Intent intent = new Intent(PrincipalActivity.this, MapsActivity.class);
        startActivity(intent);
    }

}