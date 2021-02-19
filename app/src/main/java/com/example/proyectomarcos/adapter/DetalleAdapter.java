package com.example.proyectomarcos.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Usuario;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DetalleAdapter extends ArrayAdapter<Usuario> {
    Activity context;
    ArrayList<Usuario> listaUsuarios;

    public DetalleAdapter(Fragment context, ArrayList<Usuario> listaUsuarios) {
        super(context.getActivity(), R.layout.layout_elemento_detalle_lista, listaUsuarios);
        this.context = context.getActivity();
        this.listaUsuarios = listaUsuarios;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_elemento_detalle_lista, null);

        TextView id = item.findViewById(R.id.id);
        TextView nombre = item.findViewById(R.id.nombre);
        TextView apellidos = item.findViewById(R.id.apellidos);
        TextView telefono = item.findViewById(R.id.telefono);

        Usuario usuario = (Usuario) getItem(position);
        id.setText(usuario.getId());
        nombre.setText(usuario.getNombre());
        apellidos.setText(usuario.getApellidos());
        telefono.setText(usuario.getTelefono());

        return item;
    }
}

