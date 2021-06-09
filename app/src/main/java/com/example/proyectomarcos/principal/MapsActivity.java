package com.example.proyectomarcos.principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.pojo.Usuario;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageButton boton;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        boton = findViewById(R.id.imageButton);

        //Instanciamos el usuario y recogemos su marca de la BD
        FirebaseUser fbUser = (FirebaseUser) FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("usuarios").document(fbUser.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            usuario = document.toObject(Usuario.class);
                            MarkerOptions mo = new MarkerOptions();
                            mo.position(new LatLng(usuario.getPuntos().get(0).getLatitude(),
                                    usuario.getPuntos().get(0).getLongitude()));
                            mMap.addMarker(mo.icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
                        }
                    }
                });
    }

    //Al cargarse la pantalla, mostramos nuestra ubicacion actual
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ubicacionActual();
    }

    public void ubicacionActual() {
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    //    Al pulsar el botón cogemos las coordenadas actuales i las guardamos al usuario de la BD como
//    un Array de GeoPoint y añadimos el marcador a nuestro mapa
    public void sePulsa(View v) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

        double lat = mMap.getMyLocation().getLatitude();
        double longt = mMap.getMyLocation().getLongitude();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if (usuario.getPuntos() == null) {
            usuario.setPuntos(new ArrayList<>());
            usuario.getPuntos().add(new GeoPoint(lat, longt));
            LatLng posActual = new LatLng(lat, longt);
            MarkerOptions mo = new MarkerOptions();
            mo.position(posActual);
            mo.title("Inicio del día");
            mMap.addMarker(mo.icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(posActual));
        }
        if (usuario.getPuntos() != null) {
            usuario.getPuntos().set(0, new GeoPoint(lat, longt));
            final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app
                    .AlertDialog.Builder(this);
            builder.setTitle("Confirmar cambios");
            builder.setMessage("¿Está seguro de querer cambiar su ubicación marcada?");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mMap.clear();
                    LatLng posActual = new LatLng(lat, longt);
                    MarkerOptions mo = new MarkerOptions();
                    mo.position(posActual);
                    mo.title("Inicio del día");
                    mMap.addMarker(mo.icon(BitmapDescriptorFactory
                            .fromResource(R.mipmap.ic_launcher_map_foreground)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(posActual));
                }
            });
            builder.setNegativeButton("Cancelar", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        db.collection("usuarios").document(usuario.getUid()).update("puntos", usuario.getPuntos());


    }

}