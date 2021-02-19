package com.example.proyectomarcos.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.adapter.SociosAdapter;
import com.example.proyectomarcos.pojo.Usuario;

import java.util.ArrayList;

public class Activity_Fragment_Lista extends Fragment {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ListView lstListado;
    private SociosListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_fragment_socios, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        usuarios = (ArrayList<Usuario>) getArguments().getSerializable("Usuarios");
        lstListado = (ListView) getView().findViewById(R.id.LstListado);
        lstListado.setAdapter(new SociosAdapter(this, usuarios));
        lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                if (listener != null) {
                    listener.onUsuarioSeleccionado((Usuario) lstListado.getAdapter().getItem(pos));
                }
            }
        });
    }

    public void setUsuariosListener(SociosListener listener) {
        this.listener = listener;
    }
}