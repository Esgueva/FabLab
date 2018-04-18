package com.fablabburgos.fablabburgos.controlador;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.modelo.Imagen;
import com.fablabburgos.fablabburgos.vista.galeria.GaleriaDetalleActivity;

import java.util.ArrayList;

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.DatosViewHolder>  {

    private ArrayList<Imagen> imagenes;
    private Context context;

    public GaleriaAdapter(Context context, ArrayList<Imagen> imagenes) {
        this.context = context;
        this.imagenes = imagenes;
    }

    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        View fila = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fila_galeria, parent, false);
        return new GaleriaAdapter.DatosViewHolder(fila);
    }

    @SuppressLint("CheckResult")
    @NonNull
    @Override
    public void onBindViewHolder(DatosViewHolder holder, int position) {
        Imagen imagen = imagenes.get(position);
        ImageView imageView = holder.imgFoto;
        RequestOptions options = new RequestOptions();

        options.centerCrop();
        options.override(400,300);
        options.placeholder(R.mipmap.ic_logo_round);
        options.error(R.drawable.banner_menu);


        Glide.with(context)
                .load(imagen.getUrl())
                .apply(options)
                .transition(GenericTransitionOptions.with(animationObject))

                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return (imagenes.size());
    }

    public class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgFoto;

        DatosViewHolder(View itemView) {

            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFotoF);
            itemView.setOnClickListener(this);
        }

        ImageView getImgFoto() {
            return imgFoto;
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {

                Intent intent = new Intent(context, GaleriaDetalleActivity.class);
                intent.putParcelableArrayListExtra("imagenes",imagenes);
                intent.putExtra("pos", position);
                context.startActivity(intent);
            }
        }
    }





    ViewPropertyTransition.Animator animationObject = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(1500);
            fadeAnim.start();
        }
    };
}
