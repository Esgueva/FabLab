package com.fablabburgos.fablabburgos.vista;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.db.Conexion;
import com.fablabburgos.fablabburgos.vista.contacto.ContactoActivity;
import com.fablabburgos.fablabburgos.vista.evento.EventoActivity;
import com.fablabburgos.fablabburgos.vista.galeria.GaleriaActivity;
import com.fablabburgos.fablabburgos.vista.noticia.NoticiaActivity;
import com.fablabburgos.fablabburgos.vista.socio.SocioLoginActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FloatingActionButton fab;
    TextView txtLema1,txtLema2;
    VideoView videoView;


    //ON CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtLema1 = findViewById(R.id.txtLema1);
        txtLema2 = findViewById(R.id.txtLema2);

        //SETUP HILO
        new Start().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        //MENU DRAWER
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //NAVIGATION
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //BOTON FLOTANTE
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        videoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //OPCIONES DE DRAW NAVIGATION
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(Conexion.estaOnline()) {
            int id = item.getItemId();
            if (id == R.id.nav_eventos) {

                Intent intent = new Intent(MainActivity.this, EventoActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_noticias) {

                Intent intent = new Intent(MainActivity.this, NoticiaActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_galeria) {

                Intent intent = new Intent(MainActivity.this, GaleriaActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_contacto) {

                Intent intent = new Intent(MainActivity.this, ContactoActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_socios) {
                Intent intent = new Intent(MainActivity.this, SocioLoginActivity.class);
                startActivity(intent);
            }

        }else{ Toast.makeText(getApplicationContext(),
                "Sin conexion a internet", Toast.LENGTH_LONG).show();}

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //TECLA ATRAS
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //HILO
    @SuppressLint("StaticFieldLeak")
    class Start extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                //Thread.sleep(Math.abs(500));
                Thread.sleep(Math.abs(7000));


            }
            catch( InterruptedException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            fab.setVisibility(View.VISIBLE);
            txtLema1.setVisibility(View.VISIBLE);
            txtLema2.setVisibility(View.VISIBLE);
        }
    }
}
