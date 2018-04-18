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
import com.fablabburgos.fablabburgos.modelo.Curso;

import java.util.ArrayList;


public class CursoAdapter extends RecyclerView.Adapter <CursoAdapter.DatosViewHolder> {
    private ArrayList<Curso> cursos;
    private OnItemClickListener escucha;

    public CursoAdapter(ArrayList<Curso> cursos, OnItemClickListener escucha) {
        this.cursos = cursos;
        this.escucha = escucha;
    }

    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fila_cursos, parent, false);

        return new DatosViewHolder(fila);
    }


    @Override
    public void onBindViewHolder(CursoAdapter.DatosViewHolder holder, int position) {
        Curso curso = cursos.get(position);

        holder.getNombre().setText(curso.getNombre().toString());
        holder.getDescripcion().setText(curso.getDescripcion());
        holder.getCategoria().setText(curso.getCategoria().getNombre().toString());
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }


    class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nombre, descripcion, fecha, hora,categoria, dia;

        DatosViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);

            nombre = itemView.findViewById(R.id.txtNombreFilCur);
            descripcion = itemView.findViewById(R.id.txtDescripFilCur);
            categoria = itemView.findViewById(R.id.txtCategoriaFilCur);

            itemView.setOnClickListener(this);
        }


        TextView getNombre() {
            return nombre;
        }
        TextView getDescripcion() {return descripcion; }
        TextView getCategoria() {return categoria; }


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
}
