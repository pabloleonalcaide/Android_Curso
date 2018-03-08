package com.example.pablo.marcadorbaloncesto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by pablo on 18/12/17.
 */

public class Start extends Activity {

    private EditText textLocal;
    private EditText textVisitante;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        Button button = (Button) findViewById(R.id.button);
        textLocal = (EditText) findViewById(R.id.nameLocal);
        textVisitante = (EditText) findViewById(R.id.nameVisitante);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start.this,MainActivity.class);
                intent.putExtra("local", textLocal.getText().toString());
                intent.putExtra("visitante",textVisitante.getText().toString());
                startActivity(intent);
            }
        });
    }
}
