package com.example.pablo.conversordivisa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author pablo leon alcaide
 * @version 1.0
 * Conversor de monedas sencillo
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView initial = (ImageView) findViewById(R.id.buttonInitial);

        initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Conversor.class);
                startActivity(intent);
            }
        });
    }
}
