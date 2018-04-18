package com.fablabburgos.fablabburgos.vista.socio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fablabburgos.fablabburgos.R;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.fablabburgos.fablabburgos.db.Conexion.CODE;
import static com.fablabburgos.fablabburgos.db.Conexion.CONSULTAR_SOCIO_MAIL;

public class SocioRecordarActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    EditText txtEmail;
    Button btnRecordar;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socio_recordar);

        txtEmail = findViewById(R.id.txtEmailLog);
        btnRecordar = findViewById(R.id.btnRecordarRec);

        request = Volley.newRequestQueue(getApplicationContext());
        btnRecordar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EmailValidator.getInstance().isValid(txtEmail.getText().toString().trim())) {
                    cargarWebService();
                }else{
                    Toast.makeText(getApplicationContext(),"Error en el campo email",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cargarWebService() {
        String url =CONSULTAR_SOCIO_MAIL + txtEmail.getText().toString().trim() + "&code=" + CODE;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,null,this);
        request.add(jsonObjectRequest);
        Toast.makeText(getApplicationContext(),"Se ha enviado un correo a su cuenta.",Toast.LENGTH_SHORT).show();
        finish();


    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo consultar" + error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray json = response.optJSONArray("socio");
        JSONObject jsonObject=null;

        try {
            jsonObject = json.getJSONObject(0);
            String id = jsonObject.optString("id_socio");
            if(!id.equals("0")){
                Toast.makeText(getApplicationContext(),"Se ha enviado un correo a su cuenta.",Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }












    }





}
