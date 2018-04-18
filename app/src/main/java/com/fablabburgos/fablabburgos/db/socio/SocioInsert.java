package com.fablabburgos.fablabburgos.db.socio;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.fablabburgos.fablabburgos.vista.socio.SocioRegistroActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;


// [HILO INSERT SOCIO]
public class SocioInsert extends AsyncTask<String,Void,String> {

    private ProgressDialog dialog;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private SocioRegistroActivity activity;

    public SocioInsert(SocioRegistroActivity activity, Context context){
        dialog = new ProgressDialog(activity);
        this.context=context;
        this.activity = activity;
    }


    @Override
    protected String doInBackground(String... params) {

        String cadena = params[0];
        URL url; // Url de donde queremos obtener información
        String devuelve ="";


        try {
            HttpURLConnection urlConn;

            url = new URL(cadena);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setRequestProperty("Accept", "application/json");
            urlConn.connect();


            //[OBJETO JSON]
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("user", params[1]);
            jsonParam.put("pass", params[2]);
            jsonParam.put("nombre", params[3]);
            jsonParam.put("email", params[4]);
            jsonParam.put("telefono", params[5]);
            jsonParam.put("fecha_nacimiento",params[6]);

            // [ENVIO DE PARAMETROS POR POST]
            OutputStream os = urlConn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonParam.toString());
            writer.flush();
            writer.close();

            int respuesta = urlConn.getResponseCode();
            StringBuilder result = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK) {

                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    result.append(line);
                }

                //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena

                //Accedemos al vector de resultados
                String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                if (Objects.equals(resultJSON, "1")) {
                    devuelve = "Socio Registrado";
                    activity.finish();
                } else if (Objects.equals(resultJSON, "2")) {
                    devuelve = "El email ya esta registrado";
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return devuelve;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Iniciando Registro...");
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onPostExecute(String mensaje) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
}
