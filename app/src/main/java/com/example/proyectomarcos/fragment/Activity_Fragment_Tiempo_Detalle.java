package com.example.proyectomarcos.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Day;
import com.example.proyectomarcos.pojo.Information;
import com.squareup.picasso.Picasso;

public class Activity_Fragment_Tiempo_Detalle extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_fragment_tiempo_detalle, container, false);
    }

    public void mostrarDetalle(Day d, Information info) {
        TextView txtText = (TextView) getView().findViewById(R.id.texto);
        txtText.setText(d.getText());

        TextView txtMin = (TextView) getView().findViewById(R.id.min);
        txtMin.setText(d.getTemperature_min() + " " + info.getTemperature());

        TextView txtMax = (TextView) getView().findViewById(R.id.max);
        txtMax.setText(d.getTemperature_max() + " " + info.getTemperature());

        ImageView image = (ImageView) getView().findViewById(R.id.icono);
        Picasso.get().load("https://v5i.tutiempo.net/wi/01/60/" + d.getIcon() + ".png").into(image);
    }
}