package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.extras.RecibirPDFStream;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class NormativaActivity extends AppCompatActivity {
    PDFView lectorPDF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normativa);
        String url = "https://www.dropbox.com/s/1z9kgyiv57gph25/normativaCoto.pdf?dl=0";
        WebView view = (WebView) this.findViewById(R.id.wv);
        view.getSettings().setJavaScriptEnabled(true);

        view.loadUrl(url);


//        lectorPDF = findViewById(R.id.pdfLector);
//        String urlPDF="https://www.dropbox.com/s/1z9kgyiv57gph25/normativaCoto.pdf?dl=0";

    }

}
