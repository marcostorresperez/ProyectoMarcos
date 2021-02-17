package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.proyectomarcos.R;

public class EmergenciasActivity extends AppCompatActivity {

    private Button btn112, btn062;
    private ImageButton btnGuarda, btnPresidente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencias);

        btn112 = findViewById(R.id.btn112);
        btn062 = findViewById(R.id.btn062);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnPresidente = findViewById(R.id.btnPresidente);

        btn112.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri num = Uri.parse("tel:" + "112");
                Intent i = new Intent(Intent.ACTION_CALL, num);
                startActivity(i);
            }
        });

        btn062.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = "062";
         /*       Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+numero));
                startActivity(i);

          */
                callPhoneNumber(numero);
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri num = Uri.parse("tel:" + "12345678");
                Intent i = new Intent(Intent.ACTION_CALL, num);
                startActivity(i);
            }
        });

        btnPresidente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri num = Uri.parse("tel:" + "619744299");
                Intent i = new Intent(Intent.ACTION_CALL, num);
                startActivity(i);
            }
        });


    }

    public void callPhoneNumber(String numero) {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    ActivityCompat.requestPermissions(EmergenciasActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber("062");
            } else {
            }
        }
    }
}