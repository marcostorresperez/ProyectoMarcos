package com.example.proyectomarcos.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Usuario;


public class Activity_Fragment_Detalle extends Fragment {
    public Activity_Fragment_Detalle(){}

    private View view;

    private String nombre, correo, telefono, apellido;

    private static String NOMBRE = "nombre", APELLIDO = "apellido", TELEFONO = "telefono", CORREO = "correo";

    public static Activity_Fragment_Detalle newInstance(String nombre, String correo, String apellido, String telefono) {
        Activity_Fragment_Detalle fragment = new Activity_Fragment_Detalle();
        Bundle args = new Bundle();
        args.putString(CORREO, correo);
        args.putString(APELLIDO, apellido);
        args.putString(NOMBRE, nombre);
        args.putString(TELEFONO, telefono);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(NOMBRE);
        telefono = getArguments().getString(TELEFONO);
             correo = getArguments().getString(CORREO);
              apellido = getArguments().getString(APELLIDO);

        }
    }

    //Mostramos los datos del usuario
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view =  inflater.inflate(R.layout.layout_fragment_detalle, container, false);

        TextView txtNombre = (TextView) this.view.findViewById(R.id.campoNombre);
        txtNombre.setText(nombre+" "+apellido);

        TextView txtTelefono = (TextView) this.view.findViewById(R.id.detalleTelefono);
        txtTelefono.setText(telefono);

        TextView txtCorreo = (TextView) this.view.findViewById(R.id.detalleCorreo);
        txtCorreo.setText(correo);

        return this.view;
    }


    public void mostrarDetalle(Usuario u) {

    }


}