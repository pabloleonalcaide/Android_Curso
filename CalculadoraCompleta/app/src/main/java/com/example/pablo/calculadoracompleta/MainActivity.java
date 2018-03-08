package com.example.pablo.calculadoracompleta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pablo Leon Alcaide
 * @version 1.1
 */

public class MainActivity extends AppCompatActivity {


    private double operando1=0;
    private double operando2=0;
    TextView textResultado;
    boolean suma,resta,divide,multiplica = false;

    //getter & setter operandos
    public double getOperando1() {
        return operando1;
    }

    public void setOperando1(double operando1) {
        this.operando1 = operando1;
    }

    public double getOperando2() {
        return operando2;
    }

    public void setOperando2(double operando2) {
        this.operando2 = operando2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textResultado = (TextView) findViewById(R.id.resultado);
        /* Definimos los botones numericos*/
        final Button bt0 = (Button) findViewById(R.id.buton0);
        final Button bt1 = (Button) findViewById(R.id.buton1);
        final Button bt2 = (Button) findViewById(R.id.buton2);
        final Button bt3 = (Button) findViewById(R.id.buton3);
        final Button bt4 = (Button) findViewById(R.id.buton4);
        final Button bt5 = (Button) findViewById(R.id.buton5);
        final Button bt6 = (Button) findViewById(R.id.buton6);
        final Button bt7 = (Button) findViewById(R.id.buton7);
        final Button bt8 = (Button) findViewById(R.id.buton8);
        final Button bt9 = (Button) findViewById(R.id.buton9);
        /* Definimos los botones de operaciones aritmeticas */
        Button btSuma = (Button) findViewById(R.id.Suma);
        Button btResta = (Button) findViewById(R.id.Resta);
        Button btMultiplica = (Button) findViewById(R.id.Multiplica);
        Button btDivide = (Button) findViewById(R.id.Divide);
        /* Definimos los botones especiales */
        Button btReset = (Button) findViewById(R.id.reset);
        Button btComa = (Button) findViewById(R.id.decimal);
        Button btIgual = (Button) findViewById(R.id.igual);
        //Button btnSigno = (Button) findViewById(R.id.posNeg);

//FUNCIONALIDAD DE LOS BOTONES NUMERICOS
        bt0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addNumber(bt0, textResultado);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addNumber(bt1, textResultado);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(bt2, textResultado);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(bt3, textResultado);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(bt4, textResultado);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(bt5, textResultado);
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(bt6, textResultado);
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(bt7, textResultado);
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(bt8, textResultado);
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(bt9, textResultado);
            }
        });

// FUNCIONALIDAD DE LOS OPERADORES ARITMETICOS
        btSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOperandoUno()){
                    suma = true;
                    hacerOperacion();
                }
                suma = true;
                textResultado.setText(textResultado.getText() + " + ");
            }
        });
        btResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOperandoUno()){
                    resta = true;
                    hacerOperacion();
                }
                resta = true;
                textResultado.setText(textResultado.getText()+" - ");
            }
        });
        btMultiplica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOperandoUno()){
                    multiplica = true;
                    hacerOperacion();
                }
                multiplica = true;
                textResultado.setText(textResultado.getText()+" * ");

            }
        });
        btDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOperandoUno()){
                    divide = true;
                    hacerOperacion();
                }
                divide = true;
                textResultado.setText(textResultado.getText()+" / ");
                }

        });
        btIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hacerOperacion();
                limpiarOperaciones();
            }
        });

//FUNCIONALIDAD DE LOS BOTONES ESPECIALES
/*        btComa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNumber(btComa, textResultado);
            }
        });
   */
/*
        btnSigno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOperandoUno()){
                    if(getOperando1() !=0)
                        setOperando1(-getOperando1());
                }else{
                    if(getOperando2() !=0)
                        setOperando2(-getOperando2());
                }
            }
        });*/
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperando1(0);
                setOperando2(0);
                textResultado.setText("0");
            }
        });
    }

//FUNCIONES
    /**
     * Anula las banderas de operaciones aritméticas
     */
    private void limpiarOperaciones() {
        suma = resta = multiplica = divide = false;
    }


    /**
     * Realiza una operación aritmética y muestra el resultado, reasignando banderas de operaciones
     * y valores de los operandos.
     * */
    private void hacerOperacion() {
        double valorResultado = 0;
        if(suma){
            valorResultado = getOperando1() + getOperando2();
            suma = false;
        }else if(resta){
            valorResultado = getOperando1() - getOperando2();
            resta = false;
        }else if(multiplica){
            valorResultado = getOperando1() * getOperando2();
            multiplica = false;
        }else if(divide){
            valorResultado = getOperando1() / getOperando2();
            divide = false;
        }
        textResultado.setText(Double.toString(valorResultado));
        setOperando1(valorResultado);
        setOperando2(0);
    }

    /**
     * Recoge el valor del boton pulsado y lo almacena
     * @param bt1
     * @param textResultado
     */
    private void addNumber(Button bt1, TextView textResultado) {
        int num = Integer.parseInt(bt1.getText().toString());
        if(isOperandoUno()){
                String valorUno;
                if (getOperando1() == 0) {
                    valorUno = bt1.getText().toString();
                } else {
                    valorUno = textResultado.getText() + Integer.toString(num);
                }
            setOperando1(Double.parseDouble(valorUno));
            textResultado.setText(valorUno);
        }else{
            String valorDos;
            if(getOperando2() ==0)
                valorDos = bt1.getText().toString();
            else {
                //Para evitar que un valor 2 aparezca como 2.0 al usar double, parseamos a int
                int entero = (int) getOperando2();
                valorDos = Integer.toString(entero) + Integer.toString(num);
            }
            setOperando2(Double.parseDouble(valorDos));
            //Para mostrar el contenido, extraemos de la cadena el operando1 y la operación
            //Desechando y reescribiendo el valor que había en el operando2
            String[] result = textResultado.getText().toString().split(" ");
            textResultado.setText(result[0]+" "+result[1]+" "+ valorDos);
        }
    }
    /**
     * Comprueba si hay alguna coma en el operando
     * @param operando
     * @return
     */
    private boolean esPrimeraComa(double operando) {
        String num = Double.toString(operando);
        Pattern patron = Pattern.compile(".");
        Matcher matcher = patron.matcher(num);
        return matcher.matches();
    }

    /**
     * Comprueba si se ha pulsado ya el boton de una operacion matematica
     * @return true false
     */
    private boolean isOperandoUno() {
        return suma == false && resta == false && divide == false && multiplica== false;
    }
}
