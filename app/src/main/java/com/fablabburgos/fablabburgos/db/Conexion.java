package com.fablabburgos.fablabburgos.db;

/**
 * Created by R30 on 20/03/2018.
 */

public class Conexion {

    //CONSTANTES
    private static final String SERVER = "http://developerduo.esy.es/fablab/";
    public static final String INSERT_SOCIO = SERVER + "socio/socio_insertar.php";
    public static final String CONSULTAR_SOCIO_MAIL = SERVER + "socio/socio_recupera_pass_mail_v.php?email=";
    public static final String CONSULTAR_SOCIO_LOGIN = SERVER + "socio/socio_login_v.php?email=";
    public static final String CONSULTAR_EVENTOS = SERVER + "evento/evento_consulta_all_v.php?code=";
    public static final String CONSULTAR_IMAGENES = SERVER + "galeria/galeria_consulta_all_v.php?code=";
    public static final String CONSULTAR_NOTICIAS = SERVER + "noticia/noticia_consulta_all_v.php?code=";
    public static final String CONSULTAR_CURSOS= SERVER + "curso/curso_consulta_all_v.php?code=";
    public static final String CODE = "8b5551ea922dd24625c45051c64adb50fdff91fecdf5327a02c7b0be3933965e";

    //MENTODOS CONEXION
    public static Boolean estaOnline() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
