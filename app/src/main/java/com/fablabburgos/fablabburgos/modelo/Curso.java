package com.fablabburgos.fablabburgos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Curso implements Parcelable{

    private String id;
    private String nombre;
    private String descripcion;
    private String coste;
    private String duracion;
    private int estado;
    private Socio admin;
    private Categoria categoria;

    public Curso() {
    }

    protected Curso(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        descripcion = in.readString();
        coste = in.readString();
        duracion = in.readString();
        estado = in.readInt();
        categoria = in.readParcelable(Categoria.class.getClassLoader());
    }

    public static final Creator<Curso> CREATOR = new Creator<Curso>() {
        @Override
        public Curso createFromParcel(Parcel in) {
            return new Curso(in);
        }

        @Override
        public Curso[] newArray(int size) {
            return new Curso[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCoste() {
        return coste;
    }

    public void setCoste(String coste) {
        this.coste = coste;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Socio getAdmin() {
        return admin;
    }

    public void setAdmin(Socio admin) {
        this.admin = admin;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeString(coste);
        dest.writeString(duracion);
        dest.writeInt(estado);
        dest.writeParcelable(categoria, flags);
    }
}
