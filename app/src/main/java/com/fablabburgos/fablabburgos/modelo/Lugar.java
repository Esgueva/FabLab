package com.fablabburgos.fablabburgos.modelo;

/**
 * Created by rodrigoesguevaordonez on 17/3/18.
 */

public class Lugar {

    private int id_lugar;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String ciudad;
    private float longitud;
    private float latitud;

    public Lugar() {}

    public Lugar(int lugar,String nombre,String descripcion,String direccion,String ciudad, float longitud,float latitud){
        this.id_lugar = lugar;
        this.nombre= nombre;
        this.descripcion= descripcion;
        this.direccion=direccion;
        this.ciudad=ciudad;
        this.longitud =longitud;
        this.latitud = latitud;
    }

    public int getId_lugar() {
        return id_lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public float getLongitud() {
        return longitud;
    }

    public float getLatitud() {
        return latitud;
    }
}
