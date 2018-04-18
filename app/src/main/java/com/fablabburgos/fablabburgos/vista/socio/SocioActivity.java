package com.fablabburgos.fablabburgos.vista.socio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.db.Conexion;
import com.fablabburgos.fablabburgos.vista.contacto.ContactoActivity;
import com.fablabburgos.fablabburgos.vista.curso.CursoActivity;
import com.fablabburgos.fablabburgos.vista.evento.EventoActivity;
import com.fablabburgos.fablabburgos.vista.galeria.GaleriaActivity;
import com.fablabburgos.fablabburgos.vista.noticia.NoticiaActivity;

public class SocioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        String id = getIntent().getExtras().getString("id_socio");
        Toast.makeText(getApplicationContext(),"EL ID DE USUARIO ES EL: " + id ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.socio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if(Conexion.estaOnline()) {
            int id = item.getItemId();
            if (id == R.id.nav_eventos) {

                Intent intent = new Intent(SocioActivity.this, EventoActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_noticias) {

                Intent intent = new Intent(SocioActivity.this, NoticiaActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_galeria) {

                Intent intent = new Intent(SocioActivity.this, GaleriaActivity.class);
                startActivity(intent);

            } else if (id == R.id.nav_contacto) {

                Intent intent = new Intent(SocioActivity.this, ContactoActivity.class);
                startActivity(intent);

            }else if (id == R.id.nav_cursos) {

                Intent intent = new Intent(SocioActivity.this, CursoActivity.class);
                intent.putExtra("id_socio", id);
                startActivity(intent);

            }else if (id == R.id.nav_grupos) {

                //Intent intent = new Intent(SocioActivity.this, ContactoActivity.class);
                //intent.putExtra("id_socio", id);
                //startActivity(intent);

            }else if (id == R.id.nav_proyectos) {

                //Intent intent = new Intent(SocioActivity.this, ContactoActivity.class);
                //intent.putExtra("id_socio", id);
                //startActivity(intent);

            }else if (id == R.id.nav_desconexion) {

                //Intent intent = new Intent(SocioActivity.this, ContactoActivity.class);
                //startActivity(intent);
                finish();
            }

        }else{ Toast.makeText(getApplicationContext(),
                "Sin conexion a internet", Toast.LENGTH_LONG).show();}

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
