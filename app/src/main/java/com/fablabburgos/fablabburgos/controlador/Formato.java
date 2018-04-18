package com.fablabburgos.fablabburgos.controlador;

/**
 * Created by R30 on 19/03/2018.
 */

public class Formato {

    // DE FORMATO ES A FORMATO US BBDD
    // Input: 13/08/1983 -> 1983-08-13
    public static String fechaUS(String fecha){
        //Input: 13/08/1983 -> 1983-08-13
        String[] fechaSplit = fecha.split("/");
        fecha="";

        for (int i = 0 ; i<fechaSplit.length; i++){
            fecha= fechaSplit[i] + "-" + fecha;
        }
        fecha = fecha.substring(0, fecha.length() - 1);

        return fecha;
    }

    // DE FORMATO US BBDD A ES
    // 1983-08-13 -> 13/08/1983
    public static String fechaES(String fecha){

        String[] fechaSplit = fecha.split("-");
        fecha="";

        for (int i = 0 ; i<fechaSplit.length; i++){
            fecha= "-" + fechaSplit[i]  + fecha;
        }
        fecha = fecha.substring(1, fecha.length());

        return fecha;
    }
}
