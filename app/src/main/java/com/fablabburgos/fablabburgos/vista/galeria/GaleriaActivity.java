package com.fablabburgos.fablabburgos.vista.galeria;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.controlador.GaleriaAdapter;
import com.fablabburgos.fablabburgos.modelo.Imagen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.fablabburgos.fablabburgos.db.Conexion.CODE;
import static com.fablabburgos.fablabburgos.db.Conexion.CONSULTAR_IMAGENES;


public class GaleriaActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    ProgressBar progressBar;
    RecyclerView reciclado;
    ArrayList<Imagen> imagenes;


    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        progressBar = findViewById(R.id.progressBarGal);
        reciclado = findViewById(R.id.rv_images);

    }

    @Override
    protected void onResume() {
        super.onResume();
        request = Volley.newRequestQueue(getBaseContext());
        imagenes = new ArrayList<>();


        cargarWebService();

        RecyclerView.LayoutManager l = new GridLayoutManager(this, 2);

        reciclado.setHasFixedSize(true);
        reciclado.setLayoutManager(l);

        GaleriaAdapter adapter = new GaleriaAdapter(this, imagenes);
        reciclado.setAdapter(adapter);
    }



    //CARGA WEB SERVICE
    private void cargarWebService() {
        String url = CONSULTAR_IMAGENES+CODE;

        jsonObjectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }


    //ON ERROR
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getBaseContext(),"No se pudo consultar" + error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
        progressBar.setVisibility(View.GONE);
    }

    //ON RESPONSE
    @Override
    public void onResponse(JSONObject response) {
        Imagen imagen =null;
        JSONArray json = response.optJSONArray("galeria");

        try {
            for(int i = 0; i < json.length();i++ ){
                imagen = new Imagen();
                JSONObject jsonObject=null;
                jsonObject = json.getJSONObject(i);

                imagen.setUrl(jsonObject.optString("uri"));
                imagen.setTitulo(jsonObject.optString("nombreImg"));

                imagenes.add(imagen);
            }

            GaleriaAdapter adapter = new GaleriaAdapter(this,imagenes);
            progressBar.setVisibility(View.GONE);
            reciclado.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(),"No se pudo consultar" + e.toString(),Toast.LENGTH_SHORT).show();
            Log.i("ERROR",e.toString());
            progressBar.setVisibility(View.GONE);
        }

    }

}


