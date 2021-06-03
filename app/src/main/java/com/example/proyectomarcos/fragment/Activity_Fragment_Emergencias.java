package com.example.proyectomarcos.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.principal.EmergenciasActivity;

public class Activity_Fragment_Emergencias extends Fragment {

    private Button btn112, btn062;
    private ImageButton btnGuarda, btnPresidente;
    private View view;

    public Activity_Fragment_Emergencias() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.view = inflater.inflate(R.layout.activity_emergencias, container, false);

        btn112 = this.view.findViewById(R.id.btn112);
        btn062 = this.view.findViewById(R.id.btn062);
        btnGuarda = this.view.findViewById(R.id.btnGuarda);
        btnPresidente = this.view.findViewById(R.id.btnPresidente);

        btn112.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero="112";

                callPhoneNumber(numero);
            }
        });


        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String numero="123456789";

                callPhoneNumber(numero);

            }
        });

        btnPresidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero="619744299";

                callPhoneNumber(numero);
            }
        });

//        Al pulsar un botón de llamada se realizara un Intent y llamaremos a un metodo que pide
//        los permisos necesario al Smartphone para poder realizar la llamada
        btn062.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = "062";
   /*             Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));
                *//*i.setData();*//*
                startActivity(i);*/
                callPhoneNumber(numero);
            }
        });

        return this.view;
    }

    public void callPhoneNumber(String numero) {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{
                            Manifest.permission.CALL_PHONE}, 101);
                    return;
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + numero));
                startActivity(callIntent);
            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + numero));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) callPhoneNumber("062");
        }
    }
}