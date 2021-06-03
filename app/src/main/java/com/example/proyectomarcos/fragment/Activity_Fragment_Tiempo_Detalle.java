package com.example.proyectomarcos.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Day;
import com.example.proyectomarcos.pojo.Information;
import com.squareup.picasso.Picasso;

public class Activity_Fragment_Tiempo_Detalle extends Fragment {
    private ListView lstDetalle;

    private View view;

    private Day dia;
    private Information info;
    private static final String DAY = "day", INFO = "info";

    public static Activity_Fragment_Tiempo_Detalle newInstance(Day day, Information info) {
        Activity_Fragment_Tiempo_Detalle fragment = new Activity_Fragment_Tiempo_Detalle();
        Bundle args = new Bundle();
        args.putSerializable(DAY, day);
        args.putSerializable(INFO, info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dia = (Day) getArguments().getSerializable(DAY);
            info = (Information) getArguments().getSerializable(INFO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.layout_fragment_tiempo_detalle, container, false);
        mostrarDetalle();
        return this.view;
    }

    //Mostramos todos los datos del tiempo
    public void mostrarDetalle() {
        lstDetalle = (ListView) this.view.findViewById(R.id.tiempoList);

        TextView txtText = (TextView) this.view.findViewById(R.id.texto);
        TextView txtMin = (TextView) this.view.findViewById(R.id.min);
        TextView txtMax = (TextView) this.view.findViewById(R.id.max);
        TextView txtDViento = (TextView) this.view.findViewById(R.id.dirViento);
        TextView txtVViento = (TextView) this.view.findViewById(R.id.vientoVel);
        TextView txtHumedad = (TextView) this.view.findViewById(R.id.tempHumedad);
        ImageView image = (ImageView) this.view.findViewById(R.id.icono);

        txtText.setText(dia.getText());
        txtMin.setText(dia.getTemperature_min() + " " + info.getTemperature());
        txtMax.setText(dia.getTemperature_max() + " " + info.getTemperature());
        txtDViento.setText(dia.getWind_direction());
        txtVViento.setText(String.valueOf(dia.getWind()) + " " + info.getWind().toString());
        txtHumedad.setText(dia.getHumidity() + " " + info.getHumidity());

        //Cargamos con Picasso la imagen recibida
        Picasso.get().load("https://v5i.tutiempo.net/wi/01/60/" + dia.getIcon() + ".png").into(image);
    }
}