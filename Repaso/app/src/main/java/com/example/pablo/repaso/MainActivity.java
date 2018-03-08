package com.example.pablo.repaso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador = 0;
        Button incrementa = (Button) findViewById(R.id.incrementa);
        Button decrementa = (Button) findViewById(R.id.decrementa);
        Button resetear = (Button) findViewById(R.id.resetea);

        incrementa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementaContador(view);
            }
        });
        decrementa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementaContador(view);
            }
        });
        resetear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetea(view);
            }
        });

    }


    public void mostrarResultado(){
        TextView textoResultado = (TextView) findViewById(R.id.texto);
        textoResultado.setText("Contador: "+contador);
    }
    public void incrementaContador(View vista){
        contador++;
        mostrarResultado();
    }
    public void decrementaContador(View vista){
        contador--;
        mostrarResultado();
    }
    public void resetea(View vista){
        contador = 0;
        mostrarResultado();
    }
}
