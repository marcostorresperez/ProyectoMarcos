package com.example.proyectomarcos.extras;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecibirPDFStream extends AsyncTask<String, Void, InputStream> {
    ProgressBar pBar;
    PDFView lectorPDF;

    public RecibirPDFStream(ProgressBar pBar, PDFView lectorPDF) {
        this.pBar = pBar;
        this.lectorPDF = lectorPDF;
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        InputStream inputStream = null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
            }
        } catch (IOException e) {
            return null;
        }

        return inputStream;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        lectorPDF.fromStream(inputStream).load();
        pBar.setVisibility(View.GONE);

        super.onPostExecute(inputStream);
    }
}