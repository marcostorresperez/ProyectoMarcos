package com.example.proyectomarcos.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.IDNA;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.proyectomarcos.Controlador.CtrlFile;
import com.example.proyectomarcos.R;
import com.example.proyectomarcos.adapter.AdaptadorTiempo;
import com.example.proyectomarcos.pojo.Day;
import com.example.proyectomarcos.pojo.Information;
import com.example.proyectomarcos.services.TiempoService;
/*
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
*/

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Activity_Fragment_Tiempo extends Fragment {
    private AbrirTiempoListener listener;
    private View view;
    private ListView tiempoList;
    private TiempoService service;
    private Fragment parent = this;
    private Button btnCoto, btnLocal;
/*
    private FusedLocationProviderClient fusedLocationClient;
*/



    public Activity_Fragment_Tiempo() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_tiempo, container, false);

//Con el cliente HTTP Retrpofit nos conectamos a la API del tiempo y hacemos una petición que nos
//devuelve un XML que mediante DOM lo transformamos en un objeto "Day" para si poder mostrarlo
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.tutiempo.net/")
                .addConverterFactory(ScalarsConverterFactory.create()).build();

        service = retrofit.create(TiempoService.class);

        btnLocal = view.findViewById(R.id.btnLocal);
        btnCoto = view.findViewById(R.id.btnCoto);

        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiempoList.setAdapter(null);
                mostrarTiempoLocal();
            }
        });

        btnCoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiempoList.setAdapter(null);
                mostrarTiempoCoto();
            }
        });

        mostrarTiempoCoto();

        return view;
    }

    public void mostrarTiempoCoto() {
        Call<String> call = service.getTiempoCoto();
        call.enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String tiempo = response.body();
                try {
                    if (tiempo != null) {
                        File tmp = new File(getContext().getCacheDir() + "tmpXML.xml");
                        try {
                            FileWriter fw = new FileWriter(tmp);
                            fw.write(tiempo);
                            fw.close();
                        } catch (Exception e) {
                        }
                        Document doc = CtrlFile.recuperar(tmp);
                        ArrayList<Day> dias = CtrlFile.read(doc);
                        Information info = CtrlFile.info(doc);
                        tmp.delete();

                        tiempoList = view.findViewById(R.id.tiempoList);
                        tiempoList.setAdapter(new AdaptadorTiempo(parent, dias, listener, info));
                        tiempoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {
                                if (listener != null) {
                                    listener.diaSeleccionado((Day) tiempoList.getAdapter()
                                            .getItem(position), info);
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void mostrarTiempoLocal() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


/*        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                */
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ll", "40.4178,-3.7022");

        Call<String> call = service.getTiempoLocal(map);
        call.enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String tiempo = response.body();
                try {
                    if (tiempo != null) {
                        File tmp = new File(getContext().getCacheDir() + "tmpXML.xml");
                        try {
                            FileWriter fw = new FileWriter(tmp);
                            fw.write(tiempo);
                            fw.close();
                        } catch (Exception e) {
                        }
                        Document doc = CtrlFile.recuperar(tmp);
                        ArrayList<Day> dias = CtrlFile.read(doc);
                        Information info = CtrlFile.info(doc);
                        tmp.delete();

                        tiempoList = view.findViewById(R.id.tiempoList);
                        tiempoList.setAdapter(new AdaptadorTiempo(parent, dias, listener, info));
                        tiempoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                                    long id) {
                                if (listener != null) {
                                    listener.diaSeleccionado((Day) tiempoList.getAdapter()
                                            .getItem(position), info);
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    public void setAbrirTiempoListener(AbrirTiempoListener listener) {
        this.listener = listener;
    }
}