package com.example.proyectomarcos.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.proyectomarcos.R;
import com.example.proyectomarcos.adapter.AdaptadorUsuario;
import com.example.proyectomarcos.pojo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Activity_Fragment_Lista extends Fragment {

    private UsuarioListener listener;
    private ArrayList<Usuario> datos = null;
    private ListView lstListado;
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.layout_fragment_lista, container, false);
        this.lstListado = view.findViewById(R.id.LstListado);

        Fragment parent = this;
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("usuarios").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {

//  Accedemos a la BD y cargamos todos los usuarios en un ArrayList para pasarlos al adaptador
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            datos = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Usuario usuario = document.toObject(Usuario.class);
                                datos.add(usuario);
                            }

                            lstListado.setAdapter(new AdaptadorUsuario(parent, datos));
                            lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                                    if (listener != null) {
                                        listener.onUsuarioSeleccionado((Usuario) lstListado.getAdapter()
                                                .getItem(pos));
                                    }
                                }
                            });
                        }
                    }
                });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
    }

    public void setUsuarioListener(UsuarioListener listener) {
        this.listener = listener;
    }
}