package com.example.pablo.merienda;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button)findViewById(R.id.boton);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                button.setText("se acabó");
                TextView edit1 =(TextView)findViewById(R.id.texto);
                edit1.setText("Estoy lleno!");
                ImageView imagen = (ImageView) findViewById(R.id.imagen);
          // myImgView.setImageDrawable(getResources().getDrawable(R.drawable.monkey);
                imagen.setImageDrawable(getResources().getDrawable(R.drawable.after));
            }
        });

    }
}
