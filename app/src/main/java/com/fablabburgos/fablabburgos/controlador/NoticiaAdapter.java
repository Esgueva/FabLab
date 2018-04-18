package com.fablabburgos.fablabburgos.controlador;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.modelo.Noticia;
import com.fablabburgos.fablabburgos.vista.noticia.FragmentoNoticia;

import java.util.ArrayList;

import static com.fablabburgos.fablabburgos.controlador.Formato.fechaES;


public class NoticiaAdapter extends RecyclerView.Adapter <NoticiaAdapter.DatosViewHolder> {
    private ArrayList<Noticia> noticias;
    private OnItemClickListener escucha;

    public NoticiaAdapter(ArrayList<Noticia> noticias, FragmentoNoticia escucha) {
        this.noticias = noticias;
        this.escucha = escucha;
    }

    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fila_noticias, parent, false);

        return new DatosViewHolder(fila);
    }


    @Override
    public void onBindViewHolder(NoticiaAdapter.DatosViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);
        holder.getNombre().setText(noticia.getTitular().toString());
        holder.getDescripcion().setText(noticia.getDescripcion());
        holder.getFecha().setText(fechaES(noticia.getFecha().toString()));
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }


    class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titulo, descripcion, fecha;

        DatosViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);

            titulo = itemView.findViewById(R.id.txtTituloFilNot);
            descripcion = itemView.findViewById(R.id.txtDescripFilNot);
            fecha = itemView.findViewById(R.id.txtFechaFilNot);

            itemView.setOnClickListener(this);
        }

        TextView getNombre() {
            return titulo;
        }
        TextView getDescripcion() {return descripcion; }
        TextView getFecha() {
            return fecha;
        }


        @Override
        public void onClick(View view) {
            escucha.onClick(this, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder viewHolder, int posicion);
    }

    //ANIMACION CIRCULAR EN EL RECYCLER
    @Override
    public void onViewAttachedToWindow(@NonNull DatosViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCircularReveal(holder.itemView);
    }

    private void animateCircularReveal(View itemView) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(itemView.getWidth(),itemView.getHeight());

        Animator animation = ViewAnimationUtils.createCircularReveal(itemView,centerX,centerY,startRadius,endRadius);
        itemView.setVisibility(View.VISIBLE);
        animation.start();
    }

    /*
     private void animateCircularReveal(View myView) {
        int cx = 0;
        //int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }
    */

}
