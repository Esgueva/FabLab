package com.fablabburgos.fablabburgos.vista.evento;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.controlador.EventoAdapter;
import com.fablabburgos.fablabburgos.modelo.Categoria;
import com.fablabburgos.fablabburgos.modelo.Evento;
import com.fablabburgos.fablabburgos.modelo.Lugar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.fablabburgos.fablabburgos.db.Conexion.CODE;
import static com.fablabburgos.fablabburgos.db.Conexion.CONSULTAR_EVENTOS;


public class FragmentoEvento extends Fragment implements EventoAdapter.OnItemClickListener, Response.Listener<JSONObject>,Response.ErrorListener {

    private OnFragmentInteractionListener mListener;
    RecyclerView reciclado;
    ArrayList<Evento> eventos;


    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public FragmentoEvento() {
        // Required empty public constructor
    }

    // CON CREATE
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //ON CREATE VIEW
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_evento, container, false);
        reciclado = v.findViewById(R.id.recicladoEvento);
        return v;
    }

    //ON RESUME
    @Override
    public void onResume() {
        super.onResume();

        request = Volley.newRequestQueue(getContext());
        eventos = new ArrayList<>();

        cargarWebService();

        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        reciclado.setHasFixedSize(true);
        reciclado.setLayoutManager(l);
        reciclado.setAdapter(new EventoAdapter(eventos,this));
    }

    //CARGA WEB SERVICE
    private void cargarWebService() {

        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url = CONSULTAR_EVENTOS+CODE;

        jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }


    //ON CLICK
    @Override
    public void onClick(RecyclerView.ViewHolder viewHolder, int posicion) {
        if (mListener != null) {
            Evento evento = eventos.get(posicion);
            mListener.onFragmentInteraction(evento);
        }
    }

    //ON ERROR
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se pudo consultar" + error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
        progress.hide();
    }

    //ON RESPONSE
    @Override
    public void onResponse(JSONObject response) {
        Evento evento =null;
        JSONArray json = response.optJSONArray("evento");

        try {
            for(int i = 0; i < json.length();i++ ){
                evento = new Evento();
                JSONObject jsonObject=null;
                jsonObject = json.getJSONObject(i);

                evento.setId_evento(jsonObject.optString("id_evento"));
                evento.setNombre(jsonObject.optString("nombre"));
                evento.setDescripcion(jsonObject.optString("descripcion"));
                evento.setFecha(jsonObject.optString("fecha"));
                evento.setHora(jsonObject.optString("hora"));
                evento.setOrganizador(jsonObject.optString("organizador"));
                evento.setEstado(jsonObject.optInt("estado"));
                evento.setLugar(new Lugar(jsonObject.optInt("id_lugar_FK"),jsonObject.optString("nombre_lug"),jsonObject.optString("descripcion_lug"),jsonObject.optString("direccion_lug"),jsonObject.optString("ciudad_lug"),Float.parseFloat(jsonObject.optString("longitud_lug")),Float.parseFloat(jsonObject.optString("latitud_lug"))));
                evento.setCategoria(new Categoria(jsonObject.optInt("id_categoria_FK"),jsonObject.optString("nombre_cat"),jsonObject.optString("descripcion_cat")));
                eventos.add(evento);
            }


            progress.hide();
            EventoAdapter adapter = new EventoAdapter(eventos,this);
            reciclado.setAdapter(adapter);



        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No se pudo consultar" + e.toString(),Toast.LENGTH_SHORT).show();
            Log.i("ERROR",e.toString());
            progress.hide();
        }

    }

    //ON FRAGMENT INTERACTION
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Evento evento);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
