package com.fablabburgos.fablabburgos.controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    public static String getPasswordSeguro(String password){
        String passwordGenerado = null;
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            passwordGenerado = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
        }
        return passwordGenerado;
    }

}