/**
 * @author Pablo Leon Alcaide
 * @version 1.0
 * Marcador de Baloncesto
 */
package com.example.pablo.marcadorbaloncesto;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int puntLocal=0;
    int puntVisitante =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actualizarResultado();
        //puntos local
        Button b1L = (Button) findViewById(R.id.onepointL);
        Button b2L = (Button) findViewById(R.id.twopointsL);
        Button b3L = (Button) findViewById(R.id.threepointsL);
        //puntos visitante
        Button b1V = (Button) findViewById(R.id.onepointV);
        Button b2V = (Button) findViewById(R.id.twopointsV);
        Button b3V = (Button) findViewById(R.id.threepointsV);
        //restar punto
        Button restaL = (Button) findViewById(R.id.restarLocal);
        Button restaV = (Button) findViewById(R.id.restarVisitante);

        //Operaciones
        Button reset = (Button) findViewById(R.id.reset);
        Button fin = (Button) findViewById(R.id.fin);
        TextView local = (TextView) findViewById(R.id.textLocal);
        TextView visitante = (TextView) findViewById(R.id.textVisitante);
        Bundle datos = this.getIntent().getExtras();
        local.setText(datos.getString("local"));
        visitante.setText(datos.getString("visitante"));


        /* FUNCIONES BOTONES */
        b1L.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                puntLocal++;
                actualizarResultado();

            }
        });
        b2L.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                puntLocal += 2;
                actualizarResultado();

            }
        });
        b3L.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                puntLocal +=3;
                actualizarResultado();

            }
        });
        b1V.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                puntVisitante++;
                actualizarResultado();

            }
        });
        b2V.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                puntVisitante += 2;
                actualizarResultado();

            }
        });
        b3V.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                puntVisitante +=3;
                actualizarResultado();

            }
        });
        restaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntLocal--;
                actualizarResultado();
            }
        });
        restaV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                puntVisitante--;
                actualizarResultado();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaReinicio();
            }
        });
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj = "Resultado:\nVisitante:"+Integer.toString(puntVisitante)+"\nLocal:"+Integer.toString(puntLocal);
                Toast toast =  Toast.makeText(getApplicationContext(),msj,Toast.LENGTH_SHORT);
                toast.show();
                resetPuntuacion();
                actualizarResultado();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("puntLocal",puntLocal);
        outState.putInt("puntVisitante",puntVisitante);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        puntLocal = savedInstanceState.getInt("puntLocal");
        puntVisitante = savedInstanceState.getInt("puntVisitante");
        actualizarResultado();
    }

    /**
     * Resetea la puntuación a 0 en ambos marcadores
     */
    void resetPuntuacion() {
        puntLocal = 0;
        puntVisitante=0;
    }

    private void confirmaReinicio() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage("¿Desea reiniciar los marcadores?");
        alertDialog.setTitle("Reiniciar");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                resetPuntuacion();
                actualizarResultado();

            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });


        alertDialog.show();
    }


    /**
     * Refresca el marcador
     */
    void actualizarResultado(){
        TextView tlocal = (TextView) findViewById(R.id.MarcadorLocal);
        TextView tvisitante = (TextView) findViewById(R.id.MarcadorVisitante);
        tlocal.setText(Integer.toString(puntLocal));
        tvisitante.setText(Integer.toString(puntVisitante));
    }
}
