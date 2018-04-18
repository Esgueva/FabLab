package com.fablabburgos.fablabburgos.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rodrigoesguevaordonez on 17/3/18.
 */

public class Categoria implements Parcelable {

    private int id_categoria;
    private String nombre;
    private String descripcion;
    private int cursos;
    private int eventos;
    private int galeria;
    private int proyectos;

    public Categoria() {}

    public Categoria(int id_categoria, String nombre, String descripcion){
        this.id_categoria=id_categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    protected Categoria(Parcel in) {
        id_categoria = in.readInt();
        nombre = in.readString();
        descripcion = in.readString();
        cursos = in.readInt();
        eventos = in.readInt();
        galeria = in.readInt();
        proyectos = in.readInt();
    }

    public static final Creator<Categoria> CREATOR = new Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };

    public int getId_categoria() {
        return id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCursos() {
        return cursos;
    }

    public int getEventos() {
        return eventos;
    }

    public int getGaleria() {
        return galeria;
    }

    public int getProyectos() {
        return proyectos;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCursos(int cursos) {
        this.cursos = cursos;
    }

    public void setEventos(int eventos) {
        this.eventos = eventos;
    }

    public void setGaleria(int galeria) {
        this.galeria = galeria;
    }

    public void setProyectos(int proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_categoria);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeInt(cursos);
        dest.writeInt(eventos);
        dest.writeInt(galeria);
        dest.writeInt(proyectos);
    }
}
