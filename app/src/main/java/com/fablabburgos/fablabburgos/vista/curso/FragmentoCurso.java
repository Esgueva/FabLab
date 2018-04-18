package com.fablabburgos.fablabburgos.vista.curso;

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
import com.fablabburgos.fablabburgos.controlador.CursoAdapter;
import com.fablabburgos.fablabburgos.db.Conexion;
import com.fablabburgos.fablabburgos.modelo.Categoria;
import com.fablabburgos.fablabburgos.modelo.Curso;
import com.fablabburgos.fablabburgos.modelo.Socio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.fablabburgos.fablabburgos.db.Conexion.CODE;

public class FragmentoCurso extends Fragment implements CursoAdapter.OnItemClickListener, Response.Listener<JSONObject>,Response.ErrorListener {

    private OnFragmentInteractionListener mListener;
    RecyclerView reciclado;
    ArrayList<Curso> cursos;


    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public FragmentoCurso() {
        // Required empty public constructor
    }

    // CON CREATE
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // CON CREATE VIEW
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_curso, container, false);
        reciclado = v.findViewById(R.id.recicladoCurso);
        return v;
    }

    //ON RESUME
    @Override
    public void onResume() {
        super.onResume();

        request = Volley.newRequestQueue(getContext());
        cursos = new ArrayList<>();

        cargarWebService();

        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        reciclado.setHasFixedSize(true);
        reciclado.setLayoutManager(l);
        reciclado.setAdapter(new CursoAdapter(cursos,this));
    }

    //CARGA WEB SERVICE
    private void cargarWebService() {

        progress = new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url = Conexion.CONSULTAR_CURSOS+CODE;

        jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }




    //ON CLICK
    @Override
    public void onClick(RecyclerView.ViewHolder viewHolder, int posicion) {
        if (mListener != null) {
            Curso curso = cursos.get(posicion);
            mListener.onFragmentInteraction(curso);
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

        Curso curso =null;
        JSONArray json = response.optJSONArray("curso");

        try {
            for(int i = 0; i < json.length();i++ ){
                curso = new Curso();
                JSONObject jsonObject=null;
                jsonObject = json.getJSONObject(i);

                curso.setId(jsonObject.optString("id_curso"));
                curso.setNombre(jsonObject.optString("nombre"));
                curso.setDescripcion(jsonObject.optString("descripcion"));
                curso.setCoste(jsonObject.optString("coste"));
                curso.setDuracion(jsonObject.optString("duracion"));
                curso.setAdmin(new Socio(jsonObject.optInt("id_admin_FK"),jsonObject.optString("nombre_apellidos")));
                curso.setEstado(jsonObject.optInt("estado"));
                curso.setCategoria(new Categoria(jsonObject.optInt("id_categoria_FK"),jsonObject.optString("nombre_cat"),jsonObject.optString("descripcion_cat")));
                cursos.add(curso);
            }
            progress.hide();
            CursoAdapter adapter = new CursoAdapter(cursos,this);
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
        void onFragmentInteraction(Curso curso);
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
