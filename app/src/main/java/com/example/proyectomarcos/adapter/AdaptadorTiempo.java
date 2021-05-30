package com.example.proyectomarcos.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.fragment.AbrirTiempoListener;
import com.example.proyectomarcos.pojo.Day;
import com.example.proyectomarcos.pojo.Information;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorTiempo extends ArrayAdapter<Day> {
    private Activity context;
    private List<Day> data;
    private AbrirTiempoListener listener;
    private Information info;

    public AdaptadorTiempo(Fragment context, ArrayList<Day> objects, AbrirTiempoListener listener, Information info) {
        super(context.getActivity(), R.layout.layout_tiempo_lista, objects);
        this.context = context.getActivity();
        this.data = objects;
        this.listener = listener;
        this.info = info;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View listItemView = inflater.inflate(R.layout.layout_tiempo_lista, null);

        ImageView tiempoImage = listItemView.findViewById(R.id.iempoImage);
        TextView tiempoMax = listItemView.findViewById(R.id.tiempoTemperaturaMax);
        TextView tiempoMin = listItemView.findViewById(R.id.tiempoTemperaturaMin);
        TextView tiempoViento = listItemView.findViewById(R.id.tiempoViento);

        Day dia = getItem(position);


        tiempoMin.setText(String.valueOf(dia.getTemperature_min()) + info.getTemperature());
        tiempoMax.setText(String.valueOf(dia.getTemperature_max()) + info.getTemperature());
        tiempoViento.setText(String.valueOf(dia.getWind()) + info.getWind());

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.diaSeleccionado(dia, info);
            }
        });

        return listItemView;
    }
}
