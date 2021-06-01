package com.example.proyectomarcos.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Usuario;


public class Activity_Fragment_Detalle extends Fragment {

    public Activity_Fragment_Detalle(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_fragment_detalle, container, false);
    }

    public void mostrarDetalle(Usuario u) {
        TextView txtNombre = (TextView) getView().findViewById(R.id.campoNombre);
        txtNombre.setText(u.getNombre()+" "+u.getApellido());

        TextView txtTelefono = (TextView) getView().findViewById(R.id.detalleTelefono);
        txtTelefono.setText(u.getTelefono());

        TextView txtCorreo = (TextView) getView().findViewById(R.id.detalleCorreo);
        txtCorreo.setText(u.getCorreo());
    }


}