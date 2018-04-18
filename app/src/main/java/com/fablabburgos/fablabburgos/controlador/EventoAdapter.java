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
import com.fablabburgos.fablabburgos.modelo.Evento;

import java.util.ArrayList;

import static com.fablabburgos.fablabburgos.controlador.Formato.fechaES;


public class EventoAdapter extends RecyclerView.Adapter <EventoAdapter.DatosViewHolder> {
    private ArrayList<Evento> eventos;
    private OnItemClickListener escucha;

    public EventoAdapter(ArrayList<Evento> eventos, OnItemClickListener escucha) {
        this.eventos = eventos;
        this.escucha = escucha;
    }

    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fila_eventos, parent, false);

        return new DatosViewHolder(fila);
    }


    @Override
    public void onBindViewHolder(EventoAdapter.DatosViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        String[] diaIco = eventos.get(position).getFecha().split("-");
        String dia= diaIco[2] + "/" + diaIco[1];

        holder.getDia().setText(dia);
        holder.getNombre().setText(evento.getNombre().toString());
        holder.getDescripcion().setText(evento.getDescripcion());
        holder.getFecha().setText(fechaES(evento.getFecha().toString()));
        holder.getHora().setText(evento.getHora().toString());
        holder.getCategoria().setText(evento.getCategoria().getNombre().toString());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }


    class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titulo, descripcion, fecha, hora,categoria, dia;

        DatosViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            dia = itemView.findViewById(R.id.txtDiaFilEve);
            titulo = itemView.findViewById(R.id.txtTituloFilEve);
            descripcion = itemView.findViewById(R.id.txtDescripFilEve);
            fecha = itemView.findViewById(R.id.txtFechaFilEve);
            hora = itemView.findViewById(R.id.txtHoraFilEve);
            categoria = itemView.findViewById(R.id.txtCategoriaFilEve);

            itemView.setOnClickListener(this);
        }

        TextView getDia() {
            return dia;
        }
        TextView getNombre() {
            return titulo;
        }
        TextView getDescripcion() {return descripcion; }
        TextView getFecha() {
            return fecha;
        }
        TextView getHora() {return hora; }
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
