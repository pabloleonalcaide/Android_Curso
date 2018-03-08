package com.example.pablo.conversor_examen_pabloleon;

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
 * Created by pablo on 19/12/17.
 */

public class Conversor extends Activity {
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
            Toast toast =Toast.makeText(getApplicationContext(),"Introduce algún valor", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    private double getFactor(String conversion) {
        Bundle factores = this.getIntent().getExtras();
        switch(conversion){
            case "Libra a euros":  return Double.parseDouble(factores.getString("ukEu"));
            case "Libra a dolares": return Double.parseDouble(factores.getString("ukUsa"));
            case "Libra a yenes": return Double.parseDouble(factores.getString("ukChina"));

            case "Euro a libras": return Double.parseDouble(factores.getString("euUk"));
            case "Euro a dolares": ;return Double.parseDouble(factores.getString("euUsa"));
            case "Euro a yenes": return Double.parseDouble(factores.getString("euChina"));

            case "Dolar a libras": return Double.parseDouble(factores.getString("usaUk"));
            case "Dolar a euros": ;return Double.parseDouble(factores.getString("usaEu"));
            case "Dolar a yenes": return Double.parseDouble(factores.getString("usaChina"));

            case "Yen a euros":  return Double.parseDouble(factores.getString("chinaEu"));
            case "Yen a libras": return Double.parseDouble(factores.getString("chinaUk"));
            case "Yen a dolares": return Double.parseDouble(factores.getString("chinaUsa"));

        }return 0;
    }
}
