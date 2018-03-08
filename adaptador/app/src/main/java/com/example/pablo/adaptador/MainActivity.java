package com.example.pablo.adaptador;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Lugar> datos = new ArrayList<Lugar>();
    ListView listView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list);
        insertLugares();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                TextView tv = (TextView) view.findViewById(R.id.titulo);
                Toast toast = Toast.makeText(getApplicationContext(), tv.getText().toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void insertLugares(){
        datos.add(new Lugar("mezquita","monumento árabe",getDrawable(R.drawable.a)));
        datos.add(new Lugar("sinagoga","monumento judio",getDrawable(R.drawable.b)));
        datos.add(new Lugar("alcazar","monumento histórico",getDrawable(R.drawable.c)));
        datos.add(new Lugar("puente romano","monumento romano",getDrawable(R.drawable.d)));
        datos.add(new Lugar("obispado","monumento cristiano",getDrawable(R.drawable.e)));

        Adapter adaptador = new Adapter(this,datos);
        listView = (ListView) findViewById(R.id.listaElementos);
        listView.setAdapter(adaptador);


    }

}
