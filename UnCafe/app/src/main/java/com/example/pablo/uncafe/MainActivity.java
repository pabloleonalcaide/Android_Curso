package com.example.pablo.uncafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int cantidad=0;
    final static double PRECIO_UNIDAD = 1.10;
    double precioFinal;
    String mensaje = "";
    CheckBox cbCrema;
    CheckBox cbChoco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cbCrema = (CheckBox) findViewById(R.id.checkCrema);
        cbChoco= (CheckBox) findViewById(R.id.checkChocolate);
        //Comportamiento botón menos (-)
        Button btnMenos = (Button) findViewById(R.id.buttonMenos);

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cantidad == 0){
                    return;
                }else{
                    cantidad = cantidad -1;
                    mostrarCantidad(cantidad);
                }
            }
        });

        //Comportamiento botón más (+)
        Button btnMas = (Button) findViewById(R.id.buttonMas);

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cantidad == 100){
                    return;
                }else{
                    cantidad = cantidad +1;
                    mostrarCantidad(cantidad);
                }
            }
        });

        //Comportamiento botón Order
        Button btnOrder = (Button) findViewById(R.id.buttonOrder);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensaje = buildMessage();
                calcularTotal();
                Toast toast = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    /**
     * Construye el mensaje del toast emergente
     * @return msj
     */
    private String buildMessage() {
        String msj, name,crema,choco,total;
        EditText nombre = (EditText) findViewById(R.id.textName);
        name = String.valueOf(nombre.getText());
        if(cbChoco.isChecked()){
            choco = "True";}
        else{
            choco = "False";}
        if(cbCrema.isChecked()){
            crema = "True";}
        else{
            crema="False";}
        calcularTotal();
        total = Double.toString(precioFinal);
        msj = "nombre: "+name+"\n con crema? "+crema+"\n con chocolate? "+choco+"\n Cantidad: "+Integer.toString(cantidad)+
                "\n Total: $"+total+"/n Gracias!";
        return msj;
    }

    /**
     * Calcula el total en función de las variables seleccionadas
     */
    void calcularTotal(){
        double plusCrema =0;
        double plusChoco = 0;
        double total=0;
        if(cbCrema.isChecked()){
            plusCrema = 0.25;
        }
        if(cbChoco.isChecked()){
            plusChoco = 0.25;
        }
        precioFinal = cantidad * (PRECIO_UNIDAD + plusCrema + plusChoco);

    }

    /**
     * Muestra la cantidad seleccionada por los botones de añadir/eliminar cantidad
     * @param cantidad
     */
    void mostrarCantidad(int cantidad) {
        TextView contador = (TextView) findViewById(R.id.textContador);
        contador.setText(Integer.toString(cantidad));
    }
}
