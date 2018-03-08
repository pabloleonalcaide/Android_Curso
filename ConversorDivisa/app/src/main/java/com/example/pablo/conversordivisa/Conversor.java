package com.example.pablo.conversordivisa;

import android.app.Activity;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author pablo leon alcaide
 * @version 1.0
 * Conversor de divisas sencillo
 */

public class Conversor extends Activity {
    double resultado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversor);

        Button button = (Button) findViewById(R.id.enviar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner sp = (Spinner)findViewById(R.id.spinner);
                String conversion = sp.getSelectedItem().toString();
                String[] cadena = conversion.split(" ");
                String moneda = cadena[2];
                double factor = getFactor(conversion);
                mostrarResultado(factor,moneda);
            }
        });
    }

    private void mostrarResultado(double factor, String moneda) {
        EditText et = (EditText) findViewById(R.id.input);
        TextView t = (TextView) findViewById(R.id.resultado);
        try{
            double valorInicial = Double.parseDouble(String.valueOf(et.getText()));
            double resultado = valorInicial * factor;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DecimalFormat precision = new DecimalFormat("0.00");
                resultado = Double.parseDouble(precision.format(resultado));
            }

            t.setText(Double.toString(resultado)+" "+moneda);

        }catch(Exception e){
            Toast toast =Toast.makeText(getApplicationContext(),"Not imput detected", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private double getFactor(String conversion) {
        switch(conversion){
            case "Libra a euros":  return 1.135;
            case "Euro a libras": return 0.880;
            case "Libra a dolares": return 1.139;
            case "Dolar a libras": return 0.746;
            case "Euro a dolares": ;return 1.179;
            case "Dolar a euros": ;return 0.848;
        }return 0;
    }
}
