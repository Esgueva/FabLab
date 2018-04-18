package com.fablabburgos.fablabburgos.vista.contacto;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

import com.fablabburgos.fablabburgos.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactoActivity extends AppCompatActivity implements OnMapReadyCallback {

    Button btnUrl, btnTelefono, btnMail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        MapView mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        btnUrl = findViewById(R.id.btnUrl);
        btnTelefono = findViewById(R.id.btnTelefono);
        btnMail = findViewById(R.id.btnMail);

        btnMail.setOnClickListener(click);
        btnTelefono.setOnClickListener(click);
        btnUrl.setOnClickListener(click);


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        //mGoogleMap = googleMap;
        LatLng lugar = new LatLng(42.334518, -3.707791);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lugar, 14));

        googleMap.addMarker(new MarkerOptions()
                .title("FAB LAB BURGOS")
                .snippet("Laboratorio de Fabricación Digital")
                .position(lugar)
        );
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.btnUrl:
                    Uri uriUrl = Uri.parse("http://fablabburgos.com");
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(intentWeb);
                    break;

                case R.id.btnTelefono:
                    Intent intentLlamada = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", btnTelefono.getText().toString(), null));
                    startActivity(intentLlamada);
                    break;

                case R.id.btnMail:
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + btnMail.getText().toString()));
                    //emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.solicitud_mail));
                    //emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.body_mail));
                    startActivity(Intent.createChooser(emailIntent, "CONSULTA:"));
                    break;
            }
        }
    };



}
