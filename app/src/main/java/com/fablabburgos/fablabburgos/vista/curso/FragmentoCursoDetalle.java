package com.fablabburgos.fablabburgos.vista.curso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.modelo.Curso;


public class FragmentoCursoDetalle extends Fragment {

    Curso curso;
    TextView nombre, descripcion, categoria, coste, duracion, organizador;
    Button btnGuardarCalendar;

    RecyclerView.Recycler reciladoHorario;


    public FragmentoCursoDetalle() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragmento_curso_detalle, container, false);
        if (getArguments() != null) {
            curso = (Curso) getArguments().get("curso");
            actualizar(curso, v);
        }


        return  v;
    }


    private void actualizar(Curso curso, View v) {
        nombre = v.findViewById(R.id.txtNombreCurDet);
        descripcion = v.findViewById(R.id.txtDescripCurDet);
        categoria = v.findViewById(R.id.txtCategoriaCurDet);
        organizador = v.findViewById(R.id.txtOrganizadorCurDet);
        duracion = v.findViewById(R.id.txtDuracionCurDet);
        coste = v.findViewById(R.id.txtCosteCurDet);
        //btnGuardarCalendar = v.findViewById(R.id.btnGuardarCalendar);


        nombre.setText(curso.getNombre());
        descripcion.setText(curso.getDescripcion());
        categoria.setText(curso.getCategoria().getNombre());
        organizador.setText(curso.getAdmin().getNombre_apellidos());
        duracion.setText(curso.getDuracion());
        coste.setText(curso.getCoste());

        //btnGuardarCalendar.setOnClickListener(click);
    }


    private final View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //WHILE PARA RECORRER TODOS LOS HORARIOS DEL ENVENTO:

            /*String[] fechaSplit = evento.getFecha().split("-");
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
                    "Evento añadido a su calendario", Toast.LENGTH_LONG).show();*/
        }
    };


    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void cambio(Curso curso){
        actualizar(curso,getView());
    }
}
