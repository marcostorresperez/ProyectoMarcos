package com.example.proyectomarcos.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import com.example.proyectomarcos.R;

import java.util.List;

public class NormativaActivity extends AppCompatActivity {

 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normativa);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("C:\\Users\\Usuario\\AndroidStudioProjects\\ProyectoMarcos\\app\\src\\main\\res\\extras\\normativa.pdf"));
        intent.setType("application/pdf");
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
        if (activities.size() > 0) {
            startActivity(intent);
        } else { // Do something else here. Maybe pop up a Dialog or Toast }
        }
    }

  */
}