package com.fablabburgos.fablabburgos.modelo;


import android.os.Parcel;
import android.os.Parcelable;


public class Evento implements Parcelable {
    private String id_evento;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String hora;
    private String organizador;
    private int estado;
    private Lugar lugar;
    private Categoria categoria;

    public Evento() {

    }

    //GETTER

    protected Evento(Parcel in) {
        id_evento = in.readString();
        nombre = in.readString();
        descripcion = in.readString();
        fecha = in.readString();
        hora = in.readString();
        organizador = in.readString();
        estado = in.readInt();
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };

    public String getId_evento() {
        return id_evento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getOrganizador() {
        return organizador;
    }

    public int getEstado() {
        return estado;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public Categoria getCategoria() {return categoria;}


    //SETTER

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setLugar(Lugar lugar) { this.lugar = lugar; }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_evento);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(fecha);
        dest.writeString(hora);
        dest.writeString(organizador);
        dest.writeInt(estado);
    }
}



