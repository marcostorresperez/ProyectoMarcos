package com.example.proyectomarcos.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

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

public class Activity_Fragment_Detalle extends Fragment {
    private ListView lstDetalle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_lista, container, false);
    }

    public void mostrarDatos(ArrayList<Usuario> lista) {
        lstDetalle = (ListView) getView().findViewById(R.id.LstDetalle);
        lstDetalle.setAdapter(new SociosAdapter(this, lista));

        lstDetalle.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = (Usuario) lstDetalle.getAdapter().getItem(position);
                String texto = "Nª Socio " + usuario.getId() + "\n" + "Nombre" + usuario.getNombre() + " " + usuario.getApellidos()
                        + "\n" + "Telefono " + usuario.getTelefono() + "Activo: " + usuario.getEsActivo()
                        + "Es de la junta " + usuario.getEsJunta();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.dialogs);
                LayoutInflater inflater = getActivity().getLayoutInflater();
                builder.setMessage(texto);
                builder.setTitle("Info usuario");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}