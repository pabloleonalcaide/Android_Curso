package com.example.pablo.adaptador;

import android.graphics.drawable.Drawable;

/**
 * Created by pablo on 25/01/18.
 */

public class Lugar {
    public String nombre;
    public String descripcion;
    public Drawable idImagen;

    public Lugar(String nombre, String descripcion, Drawable idImagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idImagen = idImagen;
    }
}
