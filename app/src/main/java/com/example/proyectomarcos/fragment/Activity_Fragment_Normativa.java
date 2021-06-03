package com.example.proyectomarcos.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.proyectomarcos.R;

public class Activity_Fragment_Normativa extends Fragment {

    private View view;

    public Activity_Fragment_Normativa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.activity_normativa, container, false);

        WebView view = (WebView) this.view.findViewById(R.id.wv);
        view.getSettings().setJavaScriptEnabled(true);

        view.loadUrl("https://www.dropbox.com/s/1z9kgyiv57gph25/normativaCoto.pdf?dl=0");

        return this.view;
    }
}