package com.fablabburgos.fablabburgos.vista.socio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.fablabburgos.fablabburgos.controlador.Password;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.fablabburgos.fablabburgos.db.Conexion.CODE;
import static com.fablabburgos.fablabburgos.db.Conexion.CONSULTAR_SOCIO_LOGIN;

public class SocioLoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    EditText txtEmail,txtPass;
    Button btnLogin, btnRegistro, btnRecordar;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socio_login);

        txtEmail = findViewById(R.id.txtEmailLog);
        txtPass = findViewById(R.id.txtPassLog);

        btnLogin = findViewById(R.id.btn_inicio_session);
        btnRegistro = findViewById(R.id.btn_registro_socio);
        btnRecordar = findViewById(R.id.btn_recordar_pass);

        request = Volley.newRequestQueue(getApplicationContext());

        btnLogin.setOnClickListener(click);
        btnRegistro.setOnClickListener(click);
        btnRecordar.setOnClickListener(click);

        txtEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(EmailValidator.getInstance().isValid(txtEmail.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Email valido",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }

    //ON CLICK ALTA
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_inicio_session:
                    if(EmailValidator.getInstance().isValid(txtEmail.getText().toString().trim())) {
                        cargarWebService();
                    }else{
                        Toast.makeText(getApplicationContext(),"Email invalido",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btn_recordar_pass:
                    Intent intent_recordar = new Intent(SocioLoginActivity.this, SocioRecordarActivity.class);
                    startActivity(intent_recordar);
                    break;

                case R.id.btn_registro_socio:
                    Intent intent_registro = new Intent(SocioLoginActivity.this, SocioRegistroActivity.class);
                    startActivity(intent_registro);
                    break;
            }

        }
    };

    private void cargarWebService() {
        String url =CONSULTAR_SOCIO_LOGIN + txtEmail.getText().toString().trim() + "&pass=" + Password.getPasswordSeguro(txtPass.getText().toString().trim())+ "&code=" + CODE;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
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
            if(id.equals("0")){
                Toast.makeText(getApplicationContext(),"FALLO EN EL ACCESO",Toast.LENGTH_SHORT).show();
            }else{
                //Toast.makeText(getApplicationContext(),"ABRIR NUEVA ACTIVITY PARA EL USUARIO: ID: " + id ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SocioLoginActivity.this, SocioActivity.class);
                intent.putExtra("id_socio", id);
                startActivity(intent);
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
