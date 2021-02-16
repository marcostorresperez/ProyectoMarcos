package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
                Uri num = Uri.parse("tel:" + "062");
                Intent i = new Intent(Intent.ACTION_CALL, num);
                startActivity(i);
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
}