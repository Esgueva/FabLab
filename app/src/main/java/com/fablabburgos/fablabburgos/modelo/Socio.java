package com.fablabburgos.fablabburgos.modelo;

/**
 * Created by rodrigoesguevaordonez on 18/3/18.
 */

public class Socio {

    private int id_socio;
    private String user;
    private String pass;
    private String nombre_apellidos;
    private String email;
    private String telefono;
    private String fecha_nacimiento;
    private int intentos_fallidos;
    private int bloqueado;
    private String ultima_conexion;
    private String codigo_confirmacion;
    private int cuota_pagada;


    public Socio(int id_socio, String nombre_apellidos){
        this.id_socio = id_socio;
        this.nombre_apellidos = nombre_apellidos;
    }

    public Socio(String user, String pass, String nombre_apellidos, String email, String telefono, String fecha_nacimiento) {
        this.user = user;
        this.pass = pass;
        this.nombre_apellidos = nombre_apellidos;
        this.email = email;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getId_socio() {
        return id_socio;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getNombre_apellidos() {
        return nombre_apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public int getIntentos_fallidos() {
        return intentos_fallidos;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public String getUltima_conexion() {
        return ultima_conexion;
    }

    public String getCodigo_confirmacion() {
        return codigo_confirmacion;
    }

    public int getCuota_pagada() {
        return cuota_pagada;
    }
}
