package com.example.proyectomarcos.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PrincipalFragment extends Fragment {

    public PrincipalFragment() {
        // Required empty public constructor
    }

    private TextView nombre;
    private Usuario usuario;
    private View view;
    private PrincipalListener listener;
    private Button btnMaps, btnSocios, btnNormativa, btnEmergencias, btnSalir, btnTiempo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.view = inflater.inflate(R.layout.activity_principal, container, false);

        nombre = this.view.findViewById(R.id.correoUser);
        btnTiempo = this.view.findViewById(R.id.btnTiempo);
        btnSalir = this.view.findViewById(R.id.btnSalir);
        btnEmergencias = this.view.findViewById(R.id.btnEmergencias);
        btnMaps = this.view.findViewById(R.id.btnMaps);
        btnNormativa = this.view.findViewById(R.id.btnNormativa);
        btnSocios = this.view.findViewById(R.id.btnSocios);

        FirebaseUser fbUser = (FirebaseUser) FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Guardado de datos
        SharedPreferences.Editor prefs = getContext().getSharedPreferences(getString(R.string.prefs_file),
                Context.MODE_PRIVATE).edit();
        prefs.putString("email", fbUser.getEmail());
        prefs.apply();


        //Cogemos la ultima cuenta logueada
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
        if (signInAccount != null) nombre.setText(signInAccount.getDisplayName());

        //Hacemos una petición para colocar el nombre del usuario en un TextView
        db.collection("usuarios").document(fbUser.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            usuario = document.toObject(Usuario.class);
                            nombre.setText("Bienvenido \n" + usuario.getApellido());

                            // El botón "btnSocios" solo sera visible si el usuario es de la junta de socios
                            if (!usuario.getEsJunta()) {
                                btnSocios.setVisibility(View.GONE);
                            }
                        }
                    }
                });

        btnTiempo.setOnClickListener(v -> {
            this.listener.onButtonPressed(PrincipalButtons.TIEMPO);
        });
        btnSalir.setOnClickListener(v -> {
            this.listener.onButtonPressed(PrincipalButtons.SALIR);
        });
        btnEmergencias.setOnClickListener(v -> {
            this.listener.onButtonPressed(PrincipalButtons.EMERGENCIAS);
        });
        btnSocios.setOnClickListener(v -> {
            this.listener.onButtonPressed(PrincipalButtons.SOCIOS);
        });
        btnNormativa.setOnClickListener(v -> {
            this.listener.onButtonPressed(PrincipalButtons.NORMATIVA);
        });
        btnMaps.setOnClickListener(v -> {
            this.listener.onButtonPressed(PrincipalButtons.MAPS);
        });
        return this.view;
    }

    public void setListener(PrincipalListener listener) {
        this.listener = listener;
    }
}