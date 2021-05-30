package com.example.proyectomarcos.fragment;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.proyectomarcos.Controlador.CtrlFile;
import com.example.proyectomarcos.R;
import com.example.proyectomarcos.adapter.AdaptadorTiempo;
import com.example.proyectomarcos.pojo.Day;
import com.example.proyectomarcos.pojo.Information;
import com.example.proyectomarcos.services.TiempoService;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

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

    public Activity_Fragment_Tiempo() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_tiempo, container, false);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.tutiempo.net/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


        service = retrofit.create(TiempoService.class);

        Call<String> call = service.getTiempo();

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

                        Log.d("JSONJSON", tiempo.toString());
                        tiempoList = view.findViewById(R.id.tiempoList);
                        tiempoList.setAdapter(new AdaptadorTiempo(parent, dias, listener, info));

                        tiempoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (listener != null) {
                                    listener.diaSeleccionado((Day) tiempoList.getAdapter().getItem(position), info);
                                }
                            }
                        });


                    }
                } catch (Exception e) {
                    Log.e("fallo", e.toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


        return view;
    }

    public void setAbrirTiempoListener(AbrirTiempoListener listener) {
        this.listener = listener;
    }
}