package com.example.proyectomarcos.principal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.proyectomarcos.R;
import com.example.proyectomarcos.databinding.ActivityDrawerBinding;
import com.example.proyectomarcos.fragment.AbrirTiempoListener;
import com.example.proyectomarcos.fragment.Activity_Fragment_Detalle;
import com.example.proyectomarcos.fragment.Activity_Fragment_Emergencias;
import com.example.proyectomarcos.fragment.Activity_Fragment_Lista;
import com.example.proyectomarcos.fragment.Activity_Fragment_Tiempo;
import com.example.proyectomarcos.fragment.Activity_Fragment_Tiempo_Detalle;
import com.example.proyectomarcos.fragment.Activity_Fragment_Normativa;
import com.example.proyectomarcos.fragment.PrincipalButtons;
import com.example.proyectomarcos.fragment.Activity_Fragment_Principal;
import com.example.proyectomarcos.fragment.PrincipalListener;
import com.example.proyectomarcos.fragment.UsuarioListener;
import com.example.proyectomarcos.pojo.Day;
import com.example.proyectomarcos.pojo.Information;
import com.example.proyectomarcos.pojo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class DrawerActivity extends AppCompatActivity implements PrincipalListener, UsuarioListener,
        AbrirTiempoListener, NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDrawerBinding binding;
    private Fragment currentFragment;
    private FrameLayout main_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_drawer);

        //Creamos el layout y construimos la barra superior para la navegación
        DrawerLayout drawer = binding.drawerLayout;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.emergenciasActivity, R.id.normativaActivity, R.id.principalActivity,
                R.id.usuarioActivity, R.id.tiempoActivity).setDrawerLayout(drawer).build();

        main_frame = findViewById(R.id.main_frame);
        currentFragment = new Activity_Fragment_Principal();
        ((Activity_Fragment_Principal) currentFragment).setListener(this::onButtonPressed);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, currentFragment).commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Dejamos un listener por si se pulsa cualquiera de las opciones
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        View headerView = navigationView.getHeaderView(0);
        TextView headerNombre = headerView.findViewById(R.id.drawerNombre);
        TextView headerCorreo = headerView.findViewById(R.id.drawerCorreo);

        //Capturamos el usuario actual para rellenar los datos del menú
        FirebaseUser fbUser = (FirebaseUser) FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("usuarios").document(fbUser.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            Usuario usuario = document.toObject(Usuario.class);
                            headerNombre.setText(usuario.getNombre() + " " + usuario.getApellido());
                            headerCorreo.setText(usuario.getCorreo());


                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (currentFragment instanceof Activity_Fragment_Principal) {
            SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file),
                    Context.MODE_PRIVATE).edit();
            prefs.clear();
            prefs.apply();

            FirebaseAuth.getInstance().signOut();

            finish();
        } else if (currentFragment instanceof Activity_Fragment_Detalle) {
            currentFragment = new Activity_Fragment_Lista();
            ((Activity_Fragment_Lista) currentFragment).setUsuarioListener(this::onUsuarioSeleccionado);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
        } else if (currentFragment instanceof Activity_Fragment_Tiempo_Detalle) {
            currentFragment = new Activity_Fragment_Tiempo();
            ((Activity_Fragment_Tiempo) currentFragment).setAbrirTiempoListener(this::diaSeleccionado);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
        } else {
            currentFragment = new Activity_Fragment_Principal();
            ((Activity_Fragment_Principal) currentFragment).setListener(this::onButtonPressed);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.main_frame);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onButtonPressed(PrincipalButtons button) {
        switch (button) {
            case MAPS:
                Intent intent = new Intent(DrawerActivity.this, MapsActivity.class);
                startActivity(intent);
                break;
            case SALIR:
                SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file),
                        Context.MODE_PRIVATE).edit();
                prefs.clear();
                prefs.apply();
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
            case SOCIOS:
                currentFragment = new Activity_Fragment_Lista();
                ((Activity_Fragment_Lista) currentFragment).setUsuarioListener(this::onUsuarioSeleccionado);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
                break;
            case TIEMPO:
                currentFragment = new Activity_Fragment_Tiempo();
                ((Activity_Fragment_Tiempo) currentFragment).setAbrirTiempoListener(this::diaSeleccionado);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
                break;
            case NORMATIVA:
                currentFragment = new Activity_Fragment_Normativa();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
                break;
            case EMERGENCIAS:
                currentFragment = new Activity_Fragment_Emergencias();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId() == R.id.emergenciasActivity) {
            currentFragment = new Activity_Fragment_Emergencias();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
        } else if (item.getItemId() == R.id.normativaActivity) {
            currentFragment = new Activity_Fragment_Normativa();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
        } else if (item.getItemId() == R.id.principalActivity) {
            currentFragment = new Activity_Fragment_Principal();
            ((Activity_Fragment_Principal) currentFragment).setListener(this::onButtonPressed);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
        } else if (item.getItemId() == R.id.usuarioActivity) {
            currentFragment = new Activity_Fragment_Lista();
            ((Activity_Fragment_Lista) currentFragment).setUsuarioListener(this::onUsuarioSeleccionado);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
        } else if (item.getItemId() == R.id.tiempoActivity) {
            currentFragment = new Activity_Fragment_Tiempo();
            ((Activity_Fragment_Tiempo) currentFragment).setAbrirTiempoListener(this::diaSeleccionado);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
        }
        return false;
    }

    @Override
    public void onUsuarioSeleccionado(Usuario u) {
        currentFragment = Activity_Fragment_Detalle.newInstance(u.getNombre(), u.getCorreo(),
                u.getApellido(), u.getTelefono());
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
    }

    @Override
    public void diaSeleccionado(Day day, Information info) {
        currentFragment = Activity_Fragment_Tiempo_Detalle.newInstance(day, info);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, currentFragment).commit();
    }
}