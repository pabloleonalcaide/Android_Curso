package com.example.pablo.conversor_examen_pabloleon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView chinaUsa;
    private TextView chinaUK;
    private TextView usaUk;
    private TextView usaChina;
    private TextView usaEu;
    private TextView uKchina;
    private TextView ukUsa;
    private TextView ukEu;
    private TextView euChina;
    private TextView euUSA;
    private TextView euUK;
    private TextView chinaEu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast toast = Toast.makeText(getApplicationContext(),"Valor de Cambio",Toast.LENGTH_SHORT);
        toast.show();
        chinaUsa = (TextView) findViewById(R.id.ChinaToUsa);
        chinaUK = (TextView) findViewById(R.id.ChinaToUK);
        chinaEu = (TextView) findViewById(R.id.ChinaToEurope);
        usaUk = (TextView) findViewById(R.id.UsaToUK);
        usaChina = (TextView) findViewById(R.id.UsaToChina);
        usaEu = (TextView) findViewById(R.id.UsaToEurope);
        uKchina = (TextView) findViewById(R.id.UKtoChina);
        ukUsa = (TextView) findViewById(R.id.UKtoUsa);
        ukEu = (TextView) findViewById(R.id.UkToEurope);
        euChina = (TextView) findViewById(R.id.EuropeToChina);
        euUSA = (TextView) findViewById(R.id.EuropeToUsa);
        euUK = (TextView) findViewById(R.id.EuropeToUk);


        Button button = (Button) findViewById(R.id.Go);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Conversor.class);
                intent.putExtra("chinaUsa", chinaUsa.getText().toString());
                intent.putExtra("chinaUk",chinaUK.getText().toString());
                intent.putExtra("chinaEu",chinaEu.getText().toString());

                intent.putExtra("usaChina",usaChina.getText().toString());
                intent.putExtra("usaUk",usaUk.getText().toString());
                intent.putExtra("usaEu",usaEu.getText().toString());

                intent.putExtra("ukChina",uKchina.getText().toString());
                intent.putExtra("ukEu",ukEu.getText().toString());
                intent.putExtra("ukUsa",ukUsa.getText().toString());

                intent.putExtra("euChina",euChina.getText().toString());
                intent.putExtra("euUsa",euUSA.getText().toString());
                intent.putExtra("euUk",euUK.getText().toString());

                startActivity(intent);
            }
        });
    }
}
