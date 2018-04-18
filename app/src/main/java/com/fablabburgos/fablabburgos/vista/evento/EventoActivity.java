package com.fablabburgos.fablabburgos.vista.evento;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.modelo.Evento;

public class EventoActivity extends AppCompatActivity implements FragmentoEvento.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        if (findViewById(R.id.fragmento_contenedor) != null && savedInstanceState == null){
            FragmentoEvento fragmento_evento = new FragmentoEvento();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_contenedor,fragmento_evento).commit();
        }else{
            FragmentoEvento fragmento_evento = new FragmentoEvento();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_evento,fragmento_evento).commit();

            FragmentoEventoDetalle fragmento_evento_detalle = new FragmentoEventoDetalle();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_evento_detalle,fragmento_evento_detalle).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Evento evento) {
        FragmentoEventoDetalle fragmentoEventoDetalle = (FragmentoEventoDetalle) getSupportFragmentManager().findFragmentById(R.id.fragmento_evento_detalle);
        if (fragmentoEventoDetalle != null){
            fragmentoEventoDetalle.cambio(evento);
        }else{
            fragmentoEventoDetalle = new FragmentoEventoDetalle();
            Bundle args = new Bundle();
            args.putParcelable("evento", evento);
            fragmentoEventoDetalle.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmento_contenedor,fragmentoEventoDetalle)
                    .addToBackStack(null).commit();

        }
    }
}


