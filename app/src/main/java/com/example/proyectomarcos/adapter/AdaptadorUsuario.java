package com.example.proyectomarcos.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
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

import javax.annotation.Nullable;

public class AdaptadorUsuario extends ArrayAdapter<Usuario> {
    Activity context;
    ArrayList<Usuario> datos;

    public AdaptadorUsuario(Fragment context, ArrayList<Usuario> datos) {
        super(context.getActivity(), R.layout.layout_elemento_lista, datos);
        this.context = context.getActivity();
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View listItemView = inflater.inflate(R.layout.layout_elemento_lista, null);
        Usuario usuario = getItem(position);
        TextView correo = (TextView) listItemView.findViewById(R.id.userCorreo);
        correo.setText(usuario.getCorreo());

        TextView apellido = (TextView) listItemView.findViewById(R.id.detalleApellido);
        apellido.setText(usuario.getApellido());
        Log.d("Adapter", "Usuario añadido");

        return listItemView;

    }


}

