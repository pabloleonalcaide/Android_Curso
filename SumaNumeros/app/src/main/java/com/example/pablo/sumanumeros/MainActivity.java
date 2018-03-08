package com.example.pablo.sumanumeros;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSumar = (Button)findViewById(R.id.buttonSuma);
        buttonSumar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                TextView edit1 =(TextView)findViewById(R.id.edit1);
                int num1 = Integer.parseInt(edit1.getText().toString());
                TextView edit2 =(TextView)findViewById(R.id.edit2);
                int num2 = Integer.parseInt(edit2.getText().toString());
                int resultado = (num1+num2);
                TextView textView = (TextView) findViewById(R.id.resultView);
                textView.setText(Integer.toString(resultado));
                textView.setBackgroundColor(Color.parseColor("#98fb98"));

            }
        });
        Button buttonRestar = (Button)findViewById(R.id.buttonResta);
        buttonRestar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                TextView edit1 =(TextView)findViewById(R.id.edit1);
                int num1 = Integer.parseInt(edit1.getText().toString());
                TextView edit2 =(TextView)findViewById(R.id.edit2);
                int num2 = Integer.parseInt(edit2.getText().toString());
                int resultado = (num1-num2);
                TextView textView = (TextView) findViewById(R.id.resultView);
                textView.setText(Integer.toString(resultado));
                textView.setBackgroundColor(Color.parseColor("#98fb98"));

            }
        });
        Button buttonMultiplicar = (Button)findViewById(R.id.buttonMultiplicar);
        buttonMultiplicar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                TextView edit1 =(TextView)findViewById(R.id.edit1);
                int num1 = Integer.parseInt(edit1.getText().toString());
                TextView edit2 =(TextView)findViewById(R.id.edit2);
                int num2 = Integer.parseInt(edit2.getText().toString());
                int resultado = (num1*num2);
                TextView textView = (TextView) findViewById(R.id.resultView);
                textView.setText(Integer.toString(resultado));
                textView.setBackgroundColor(Color.parseColor("#98fb98"));

            }
        });
        Button buttonDividir = (Button)findViewById(R.id.buttonDivision);
        buttonDividir.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                TextView edit1 =(TextView)findViewById(R.id.edit1);
                int num1 = Integer.parseInt(edit1.getText().toString());
                TextView edit2 =(TextView)findViewById(R.id.edit2);
                int num2 = Integer.parseInt(edit2.getText().toString());
                int resultado = (num1/num2);
                TextView textView = (TextView) findViewById(R.id.resultView);
                textView.setText(Integer.toString(resultado));
                textView.setBackgroundColor(Color.parseColor("#98fb98"));

            }
        });
    }
}
