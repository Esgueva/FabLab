package com.fablabburgos.fablabburgos.vista.galeria;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.controlador.GaleriaAdapter;
import com.fablabburgos.fablabburgos.modelo.Imagen;

import java.util.ArrayList;

public class GaleriaDetalleActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView txtNombreImg;
    Button btnSiguiente,btnAtras;
    ArrayList<Imagen> imagenes;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_detalle);
        mImageView = findViewById(R.id.iv_photo);
        txtNombreImg = findViewById(R.id.txtNombreImg);
        btnAtras = findViewById(R.id.btnAtrasGal);
        btnSiguiente = findViewById(R.id.btnSiguienteGal);

        btnAtras.setOnClickListener(click);
        btnSiguiente.setOnClickListener(click);

        imagenes = getIntent().getParcelableArrayListExtra("imagenes");
        position = getIntent().getIntExtra("pos",0);

        actualizar(this);

    }

    //ACTUALIZAR
    @SuppressLint("CheckResult")
    public void actualizar(Activity activity) {

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.banner_menu);
        options.error(R.drawable.banner_menu);

        Glide.with(this)
                .load(imagenes.get(position).getUrl())
                .apply(options)
                .transition(GenericTransitionOptions.with(animationObject))
                .into(mImageView);

        txtNombreImg.setText(imagenes.get(position).getTitulo());
    }





    //TRANSICION FACE
    ViewPropertyTransition.Animator animationObject = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(1500);
            fadeAnim.start();
        }
    };


    //ON CLICK
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnSiguienteGal:
                     if (position == imagenes.size()-1){
                        Toast.makeText(getApplicationContext(),"Es la ultima imagen",Toast.LENGTH_SHORT).show();
                     }else{
                        position++;
                     }
                     break;

                case R.id.btnAtrasGal:
                    if (position == 0){
                        Toast.makeText(getApplicationContext(),"Es la primera imagen",Toast.LENGTH_SHORT).show();
                    }else{
                        position--;
                    }
                    break;
            }
            actualizar(GaleriaDetalleActivity.this);
        }
    };


}
