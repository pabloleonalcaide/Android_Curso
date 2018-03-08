package com.example.pablo.conectaCuatro2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by pablo on 8/02/18.
 */

public class interfazOnline extends Activity {
    public String urlserve = "192.168.115.169"; //no localhost, sino la conexión que tengamos
    RequestQueue queue;
    private ArrayList<String> partida;
    private ListView partidas;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_online);
        queue = VolleySingleton.getInstance(this).getRequestQueue(); //asignamos la cola
        Button btAnadir = (Button) findViewById(R.id.anadir);
        partidas = (ListView) findViewById(R.id.partidas); // ListView que existe en select_online.xml
        //ESTO ES PARA QUE REFRESQUE EL MOSTRAR PARTIDA CADA X TIEMPO
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mostrarPartidas();
                    }
                });
            }
        },3000, 5000);

        btAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearPartida();
            }
        });
        //cuando hacemos click en una de las partidas de la lista
        partidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = (String) parent.getItemAtPosition(position);
                String[] arrItem = item.split("\\s");
                String idPartida = arrItem[1];
                Intent intent = new Intent(interfazOnline.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putString("ID", idPartida);
                b.putString("COLOR", "2");
                b.putString("ESTAD", "E");
                intent.putExtras(b);
                startActivity(intent);
                /*Toast toast = Toast.makeText(getApplicationContext(), idPartida, Toast.LENGTH_LONG);
toast.show();*/
            }
        });

    }
    /**
     * Creación de una nueva partida
     */
    private void crearPartida() {
        String url = "http://"+urlserve+"/conecta4/start.php";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    String idPartida = "";
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); //creamos una factoria
                    DocumentBuilder db = dbf.newDocumentBuilder();  //documento dentro de la factoria
                    InputSource is = new InputSource();  //esto actúa como buffer de entrada
                    //al objeto "is" le asignamos lo que devuelve la petición que hemos hecho al servidor
                    is.setCharacterStream(new StringReader(response));
                    Document doc= db.parse(is); //Se parsea la cadena
                    NodeList nodes = doc.getElementsByTagName("game"); //nos quedamos con los nodos de etiqueta game
                    for (int i = 0; i < nodes.getLength(); i++){
                        Element element = (Element) nodes.item(i);
                        idPartida = element.getAttribute("id");
                    }

                  Intent intent = new Intent(interfazOnline.this,MainActivity.class); //Para empezar la partida
                    Bundle b = new Bundle();
                    b.putString("ID", idPartida);
                    b.putString("COLOR","1");
                    b.putString("ESTADO","J");
                    b.putString("MODO", "ONLINE");
                    intent.putExtras(b);
                    startActivity(intent);

                } catch (Exception e) {
                        e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error para crear partida",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }

    /**
     * Muestra las partidas activas en el servidor
     */
    private void mostrarPartidas() {
        partida = new ArrayList<>();
        String url = "http://"+urlserve+"/conecta4/games.php";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //onResponse escucha lo que nos devuelve el servidor de la petición enviada
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); //creamos una factoria
                    DocumentBuilder db = dbf.newDocumentBuilder();  //documento dentro de la factoria
                    InputSource is = new InputSource();  //esto actúa como buffer de entrada
                    //al objeto "is" le asignamos lo que devuelve la petición que hemos hecho al servidor
                    is.setCharacterStream(new StringReader(response));
                    Document doc= db.parse(is); //Se parsea la cadena
                    NodeList nodes = doc.getElementsByTagName("game"); //nos quedamos con los nodos de etiqueta game
                    for (int i = 0; i < nodes.getLength(); i++){
                        Element element = (Element) nodes.item(i);
                        partida.add("Partida" + element.getAttribute("id")+"\n");
                    }
                    //Creado previamente un layout simpletext con un textview de id=simpletexto
                    ArrayAdapter myAdapter = new ArrayAdapter(getApplicationContext(),R.layout.simpletext,R.id.simpleTexto,partida);
                    partidas.setAdapter(myAdapter); //Inflamos el listview
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error para mostrar partidas",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }
}
