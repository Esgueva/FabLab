package com.fablabburgos.fablabburgos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Imagen implements Parcelable {

    private String url;
    private String titulo;

    public Imagen() {

    }

    public Imagen(String url, String titulo) {
        this.url = url;
        titulo = titulo;
    }

    protected Imagen(Parcel in) {
        url = in.readString();
        titulo = in.readString();
    }

    public static final Creator<Imagen> CREATOR = new Creator<Imagen>() {
        @Override
        public Imagen createFromParcel(Parcel in) {
            return new Imagen(in);
        }

        @Override
        public Imagen[] newArray(int size) {
            return new Imagen[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String title) {
        titulo = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(titulo);
    }

}