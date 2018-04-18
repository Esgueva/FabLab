package com.fablabburgos.fablabburgos.vista.noticia;

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
import com.fablabburgos.fablabburgos.controlador.NoticiaAdapter;
import com.fablabburgos.fablabburgos.modelo.Categoria;
import com.fablabburgos.fablabburgos.modelo.Noticia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.fablabburgos.fablabburgos.db.Conexion.CODE;
import static com.fablabburgos.fablabburgos.db.Conexion.CONSULTAR_NOTICIAS;

public class FragmentoNoticia extends Fragment implements NoticiaAdapter.OnItemClickListener,Response.Listener<JSONObject>, Response.ErrorListener{

    private FragmentoNoticia.OnFragmentInteractionListener mListener;
    RecyclerView reciclado;
    ArrayList<Noticia> noticias;


    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public FragmentoNoticia() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_noticia, container, false);
        reciclado = v.findViewById(R.id.recicladoNoticia);
        return v;
    }



    @Override
    public void onResume() {
        super.onResume();

        request = Volley.newRequestQueue(getContext());
        noticias = new ArrayList<>();

        cargarWebService();

        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        reciclado.setHasFixedSize(true);
        reciclado.setLayoutManager(l);
        reciclado.setAdapter(new NoticiaAdapter(noticias,this));
    }

    private void cargarWebService() {

        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url = CONSULTAR_NOTICIAS+CODE;

        jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }


    @Override
    public void onResponse(JSONObject response) {
        Noticia noticia =null;
        JSONArray json = response.optJSONArray("noticia");

        try {
            for(int i = 0; i < json.length();i++ ){
                noticia = new Noticia();
                JSONObject jsonObject=null;
                jsonObject = json.getJSONObject(i);

                noticia.setId_noticia(jsonObject.optString("id_noticia"));
                noticia.setTitular(jsonObject.optString("titular"));
                noticia.setDescripcion(jsonObject.optString("descripcion"));
                noticia.setUrl(jsonObject.optString("url"));
                noticia.setFecha(jsonObject.optString("fecha"));
                noticia.setCategoria(new Categoria(jsonObject.optInt("id_categoria_FK"),jsonObject.optString("nombre_cat"),jsonObject.optString("descripcion_cat")));
                noticias.add(noticia);
            }


            progress.hide();
            NoticiaAdapter adapter = new NoticiaAdapter(noticias,this);
            reciclado.setAdapter(adapter);



        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"No se pudo consultar" + e.toString(),Toast.LENGTH_SHORT).show();
            Log.i("ERROR",e.toString());
            progress.hide();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se pudo consultar" + error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
        progress.hide();

    }

    //ON CLICK
    @Override
    public void onClick(RecyclerView.ViewHolder viewHolder, int posicion) {
        if (mListener != null) {
            Noticia noticia = noticias.get(posicion);
            mListener.onFragmentInteraction(noticia);
        }
    }

    //ON FRAGMENT INTERACTION
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Noticia noticia);
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
