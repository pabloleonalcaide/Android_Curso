package com.example.pablo.adaptador;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 25/01/18.
 */

public class Adapter extends ArrayAdapter {
    ArrayList <Lugar>lugares;
    int R_layout_IdView;
    Context contexto;

    public Adapter(Context contexto, ArrayList sitios) {
        super(contexto, 0, sitios);
        lugares = sitios;


    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);
        }
        Lugar lugar = lugares.get(position);
        TextView titulo = view.findViewById(R.id.titulo);
        titulo.setText(lugar.nombre);
        TextView subtitulo = view.findViewById(R.id.subtitulo);
        subtitulo.setText(lugar.descripcion);
        ImageView image = (ImageView)view.findViewById(R.id.image);
        image.setImageDrawable(lugar.idImagen);
        return view;
    }
}
