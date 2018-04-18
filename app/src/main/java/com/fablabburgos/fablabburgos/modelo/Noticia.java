package com.fablabburgos.fablabburgos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Noticia implements Parcelable {

    private String id_noticia;
    private String titular;
    private String descripcion;
    private String url;
    private String fecha;
    private Categoria categoria;


    public Noticia() {
    }

    protected Noticia(Parcel in) {
        id_noticia = in.readString();
        titular = in.readString();
        descripcion = in.readString();
        url = in.readString();
        fecha = in.readString();
        categoria = in.readParcelable(Categoria.class.getClassLoader());
    }

    public static final Creator<Noticia> CREATOR = new Creator<Noticia>() {
        @Override
        public Noticia createFromParcel(Parcel in) {
            return new Noticia(in);
        }

        @Override
        public Noticia[] newArray(int size) {
            return new Noticia[size];
        }
    };

    public String getId_noticia() {
        return id_noticia;
    }

    public void setId_noticia(String id_noticia) {
        this.id_noticia = id_noticia;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
        dest.writeString(id_noticia);
        dest.writeString(titular);
        dest.writeString(descripcion);
        dest.writeString(url);
        dest.writeString(fecha);
        dest.writeParcelable(categoria, flags);
    }
}
