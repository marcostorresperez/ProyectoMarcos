package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.fragment.Activity_Fragment_Tiempo_Detalle;
import com.example.proyectomarcos.pojo.Day;
import com.example.proyectomarcos.pojo.Information;

public class ActivityTiempoDetalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_tiempo_detalle);

        Activity_Fragment_Tiempo_Detalle detalle=(Activity_Fragment_Tiempo_Detalle)getSupportFragmentManager().findFragmentById(R.id.FrgTiempoDetalle);

        detalle.mostrarDetalle((Day)getIntent().getSerializableExtra("TiempoDetalleDia"), (Information) getIntent().getSerializableExtra("TiempoDetalleInfo"));

    }
}