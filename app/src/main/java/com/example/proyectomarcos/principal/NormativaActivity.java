package com.example.proyectomarcos.principal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.proyectomarcos.R;
import com.google.android.gms.tasks.OnCompleteListener;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class NormativaActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normativa);
        WebView view = (WebView) this.findViewById(R.id.wv);
        view.getSettings().setJavaScriptEnabled(true);

        view.loadUrl("https://www.dropbox.com/s/1z9kgyiv57gph25/normativaCoto.pdf?dl=0");


    }


}
