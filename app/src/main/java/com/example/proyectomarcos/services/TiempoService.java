package com.example.proyectomarcos.services;

import java.io.File;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface TiempoService {
    @GET("xml/?lan=es&apid=z5EzXXz4aaarxt4&lid=931")
    Call<String> getTiempoCoto();
    @GET("xml/?lan=es&apid=z5EzXXz4aaarxt4")
    Call<String> getTiempoLocal(@QueryMap Map<String, String> ll);
}
