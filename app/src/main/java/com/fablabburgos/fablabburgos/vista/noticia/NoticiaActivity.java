package com.fablabburgos.fablabburgos.vista.noticia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.modelo.Noticia;

public class NoticiaActivity extends AppCompatActivity implements FragmentoNoticia.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);

        if (findViewById(R.id.fragmento_contenedor_noticias) != null && savedInstanceState == null){
            FragmentoNoticia fragmento_noticia = new FragmentoNoticia();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_contenedor_noticias,fragmento_noticia).commit();
        }else{
            FragmentoNoticia fragmento_noticia = new FragmentoNoticia();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_noticia,fragmento_noticia).commit();

            FragmentoNoticiaDetalle fragmento_noticia_detalle = new FragmentoNoticiaDetalle();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_noticia_detalle,fragmento_noticia_detalle).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Noticia noticia) {
        FragmentoNoticiaDetalle FragmentoNoticiaDetalle = (FragmentoNoticiaDetalle) getSupportFragmentManager().findFragmentById(R.id.fragmento_noticia_detalle);
        if (FragmentoNoticiaDetalle != null){
         //   FragmentoNoticiaDetalle.cambio(noticia);
        }else{
            FragmentoNoticiaDetalle = new FragmentoNoticiaDetalle();
            Bundle args = new Bundle();
            args.putParcelable("noticia", noticia);
            FragmentoNoticiaDetalle.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmento_contenedor_noticias,FragmentoNoticiaDetalle)
                    .addToBackStack(null).commit();

        }
    }
}
