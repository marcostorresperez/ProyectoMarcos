package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.fragment.AbrirTiempoListener;
import com.example.proyectomarcos.fragment.Activity_Fragment_Tiempo;
import com.example.proyectomarcos.fragment.Activity_Fragment_Tiempo_Detalle;
import com.example.proyectomarcos.pojo.Day;
import com.example.proyectomarcos.pojo.Information;

public class TiempoActivity extends AppCompatActivity implements AbrirTiempoListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_tiempo);

        Activity_Fragment_Tiempo frgTiempo = new Activity_Fragment_Tiempo();
        frgTiempo.setAbrirTiempoListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.FrgTiempo, frgTiempo).commit();
    }

    @Override
    public void diaSeleccionado(Day day, Information info) {
        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.FrgTiempoDetalle) != null);

        if(hayDetalle){

        }else{
            Intent i= new Intent(this, ActivityTiempoDetalle.class);
            i.putExtra("TiempoDetalleDia",day);
            i.putExtra("TiempoDetalleInfo", info);
            startActivity(i);
        }
    }
}