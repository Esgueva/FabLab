package com.fablabburgos.fablabburgos.vista.evento;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.controlador.Formato;
import com.fablabburgos.fablabburgos.modelo.Evento;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;


public class FragmentoEventoDetalle extends Fragment implements OnMapReadyCallback {

    Evento evento;

    TextView nombre, descripcion, categoria, fecha, hora, organizador, nombreLug, descripcionLug, direccionLug, ciudadLug;
    Button btnEvento;

    public FragmentoEventoDetalle() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_evento_detalle, container, false);
        if (getArguments() != null) {
            evento = (Evento) getArguments().get("evento");
            actualizar(evento, v);

            MapView mapView = v.findViewById(R.id.map);
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        return v;
    }

    private void actualizar(Evento evento, View v) {
        nombre = v.findViewById(R.id.txtNombreEveDet);
        descripcion = v.findViewById(R.id.txtDescripEveDet);
        categoria = v.findViewById(R.id.txtCategoriaEveDet);
        fecha = v.findViewById(R.id.txtFechaEveDet);
        hora = v.findViewById(R.id.txtHoraEveDet);
        organizador = v.findViewById(R.id.txtOrganizadorEveDet);
        nombreLug = v.findViewById(R.id.txtNombreLugEveDet);
        descripcionLug = v.findViewById(R.id.txtDescripLugEveDet);
        direccionLug = v.findViewById(R.id.txtDireccionLugEveDet);
        ciudadLug = v.findViewById(R.id.txtCiudadLugEveDet);
        btnEvento = v.findViewById(R.id.btnEvento);

        nombre.setText(evento.getNombre());
        descripcion.setText(evento.getDescripcion());
        categoria.setText(evento.getCategoria().getNombre());

        hora.setText("A las " + evento.getHora() + " horas ");
        fecha.setText("El dia " + Formato.fechaES(evento.getFecha()));
        organizador.setText(evento.getOrganizador());
        nombreLug.setText(evento.getLugar().getNombre());
        descripcionLug.setText(evento.getLugar().getDescripcion());
        direccionLug.setText(evento.getLugar().getDireccion());
        ciudadLug.setText(evento.getLugar().getCiudad());

        btnEvento.setOnClickListener(click);
    }


    private final View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String[] fechaSplit = evento.getFecha().split("-");
            String[] horaSplit = evento.getHora().split(":");

            Calendar beginTime = Calendar.getInstance();
            beginTime.set(Integer.parseInt(fechaSplit[0]), Integer.parseInt(fechaSplit[1])-1, Integer.parseInt(fechaSplit[2]), Integer.parseInt(horaSplit[0]), Integer.parseInt(horaSplit[1]));


            ContentValues l_event = new ContentValues();
            l_event.put("calendar_id", 1);
            l_event.put("title", evento.getNombre());
            l_event.put("description",  evento.getDescripcion());
            l_event.put("eventLocation", evento.getLugar().getNombre());
            l_event.put("dtstart", beginTime.getTimeInMillis());
            l_event.put("dtend", beginTime.getTimeInMillis());
            l_event.put("allDay", 0);
            //l_event.put("rrule", "FREQ=DAILY;COUNT=1");
            // status: 0~ tentative; 1~ confirmed; 2~ canceled
            // l_event.put("eventStatus", 1);

            l_event.put("eventTimezone", "Europe/Madrid");
            Uri l_eventUri;
            if (Build.VERSION.SDK_INT >= 8) {
                l_eventUri = Uri.parse("content://com.android.calendar/events");
            } else {
                l_eventUri = Uri.parse("content://calendar/events");
            }
            Uri l_uri = getActivity().getContentResolver()
                    .insert(l_eventUri, l_event);
            Toast.makeText(getActivity().getBaseContext(),
                    "Evento añadido a su calendario", Toast.LENGTH_LONG).show();
        }
    };



    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void cambio(Evento evento){
        actualizar(evento,getView());
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        //mGoogleMap = googleMap;
        LatLng lugar = new LatLng(evento.getLugar().getLatitud(), evento.getLugar().getLongitud());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lugar, 14));

        googleMap.addMarker(new MarkerOptions()
                .title(evento.getLugar().getNombre())
                .snippet(evento.getLugar().getDescripcion())
                .position(lugar)
        );
    }


}
