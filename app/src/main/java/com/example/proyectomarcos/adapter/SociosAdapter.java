package com.example.proyectomarcos.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SociosAdapter extends ArrayAdapter<Usuario> {

    Activity context;
    ArrayList<Usuario> listaUsuarios;

    public SociosAdapter(Fragment context, ArrayList<Usuario> listaUsuarios) {
        super(context.getActivity(), R.layout.layout_elemento_socio_lista, listaUsuarios);
        this.context = context.getActivity();
        this.listaUsuarios = listaUsuarios;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.layout_elemento_socio_lista, null);

        TextView id = item.findViewById(R.id.id);
        TextView nombre = item.findViewById(R.id.nombre);
        TextView apellidos = item.findViewById(R.id.apellidos);

        Usuario usuario = (Usuario) getItem(position);

        id.setText(usuario.getId());
        nombre.setText(usuario.getNombre());
        apellidos.setText(usuario.getApellidos());
        return (item);
    }
}

