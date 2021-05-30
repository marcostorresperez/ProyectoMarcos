package com.example.proyectomarcos.services;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TiempoService {
    @GET("/xml/?lan=es&apid=z5EzXXz4aaarxt4&lid=931")
    Call<String> getTiempo();
}
