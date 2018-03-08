package com.example.pablo.conectaCuatro2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.Build.ID;

/**
 * @author pablo leon alcaide
 * @version 1.2
 */
public class MainActivity extends Activity implements OnClickListener{
	private final int ids[][] = {{R.id.a1, R.id.a2,R.id.a3,R.id.a4,R.id.a5,R.id.a6,R.id.a7},
			{R.id.b1, R.id.b2,R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7},
			{R.id.c1, R.id.c2,R.id.c3,R.id.c4,R.id.c5,R.id.c6,R.id.c7},
			{R.id.d1, R.id.d2,R.id.d3,R.id.d4,R.id.d5,R.id.d6,R.id.d7},
			{R.id.e1, R.id.e2,R.id.e3,R.id.e4,R.id.e5,R.id.e6,R.id.e7},
			{R.id.f1, R.id.f2,R.id.f3,R.id.f4,R.id.f5,R.id.f6,R.id.f7}};
	
	private Game game;
	private ImageButton validateButton;
	private TextView resultadoTextView;
	private String idPartida;
	private String numJugador;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);
		game = new Game();
		final Bundle b = this.getIntent().getExtras();
		TextView info = (TextView) findViewById(R.id.parametros);
		/*if (!b.getString("MODO").equals("ONLINE")) {
			idPartida = b.getString("ID");
			info.setText(b.getString("MODO")+"\n"+idPartida);
		}
*/


		String x [] = getResources().getStringArray(R.array.colores);
		for(int i = 0; i< Game.NFILAS;  i++){
			for (int j = 0; j < Game.NCOLUMNAS;  j++){
				validateButton = (ImageButton) findViewById(ids[i][j]);
				validateButton.setOnClickListener(this);
			}
		}	
		resultadoTextView = (TextView) findViewById(R.id.parametros);
	}
	
	public void onSaveInstanceState(Bundle outState){
		//te permite grabar una cadena en formato clave-valor
		outState.putString("TABLERO", game.tableroACadena());
		super.onSaveInstanceState(outState);
	}
	
	public void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String grid = savedInstanceState.getString("TABLERO");
        game.cadenaATablero(grid);
        pintarTablero();
    }
	

	protected void onResume(){
		super.onResume();
		Boolean play;
		String player;

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		play = getPlayMusic(sharedPreferences);
		player = getPlayerName(sharedPreferences);

		if(player.trim().length()>0)
			resultadoTextView.setText("Pablo");
		else
			resultadoTextView.setText("");

		if (play == true)
			Music.play(this, R.raw.musica);
	   	pintarTablero();
	   	
    }
	public String getPlayerName(SharedPreferences sharedPreferences){
		String name = "";

		if(sharedPreferences.contains(PrefActivity.PLAYER_KEY)){
			name = sharedPreferences.getString(PrefActivity.PLAYER_KEY, PrefActivity.PLAYER_DEFAULT);
		}
		return name;
	}

	public Boolean getPlayMusic(SharedPreferences sharedPreferences){
		Boolean music = true;
		if (sharedPreferences.contains(PrefActivity.PLAY_MUSIC_KEY))
			music = sharedPreferences.getBoolean(PrefActivity.PLAY_MUSIC_KEY,
					PrefActivity.PLAY_MUSIC_DEFAULT);
		return music;
	}
    public String getColor (SharedPreferences sharedPreferences) {
		String color = "#00ff00";

	   	return color;
    }

    public int getColorById(String c){
    	int color = Integer.parseInt(c);
    	int color_id = R.color.gray;
    	
    	switch(color){
    	    case 1:
    	    	color_id = R.color.blue;
    	    	break;
    	    case 2:
    	    	color_id = R.color.purple;
    	    	break;
    	    case 3:
    	    	color_id = R.color.orange;
    	    	break;
    	    case 4:
    	    	color_id = R.color.red;
    	    	break;
    	}
    	return color_id;
    }

    
    protected void onPause(){
		super.onPause();
		Music.stop(this);
    }

	
   public void pintarTablero() {
       int id = 0;
       int color;
       SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

       for (int i = 0; i < Game.NFILAS; i++)
           for (int j = 0; j < Game.NCOLUMNAS; j++) {
        	   if(game.returnCasilla(i,j)==Game.MAQUINA){
        		   id = R.color.yellow;
        	   }
        	   else if(game.returnCasilla(i,j) == Game.JUGADOR){
        		   id = R.color.purple;
        	   }
        	   else{
        		   id = R.color.light_blue;
        	   }
               ImageButton imageButton = (ImageButton) findViewById(ids[i][j]);
               GradientDrawable drawable = (GradientDrawable) imageButton.getDrawable();
               drawable.setColor(getResources().getColor(id));
           }
   }
   
	public void onClick (View v){
		pulsado(v);
	}
   public void pulsado(View v) {
       int fila, columna, id = v.getId();
       
       fila = deIdentificadorAFila(id);
       columna = deIdentificadorAColumna(id);
       

       if (game.puedeColocar(fila, columna) != true) {
           Toast.makeText(this, "no puedes colocar ficha",
                   Toast.LENGTH_SHORT).show();
           return;
       }

       game.ponerFicha(fila, columna);

	   //ACTIVIDAD QUE SE DESPLIEGA CON EL CUADRO DE DIÁLOGO -->
       
       if(game.checkWinnerGame(Game.JUGADOR, fila, columna)){
		 DialogFragment dialog =DialogFragment.newInstance(R.string.titleExit);
		 dialog.show(getFragmentManager(), String.valueOf(R.string.exit_message));
       }
       else{
	       int posicion [] = game.turnoMaquina();
	       
	       if(game.checkWinnerGame(Game.MAQUINA, posicion[0], posicion[1])){
	    	   pintarTablero();
	    	   DialogFragment.newInstance(R.string.winmachine).show(getFragmentManager(), "ALERT DIALOG");
	       }
	       else{
	    	   pintarTablero();
	    	   if (game.tableroLleno()) {
	   			DialogFragment.newInstance(R.string.tableroLLeno).show(getFragmentManager(), "ALERT DIALOG");
	   			return;
	   		}
	       }
       }
   }
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAbout:
                startActivity(new Intent(MainActivity.this,About.class));
                return true;
            case R.id.menuConf:
					startActivity(new Intent (this,SettingActivity.class));
                return true;
            case R.id.menuMensaje:
				sendMessage();
        }
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Envía un mensaje
	 */
	private void sendMessage() {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
		sendIntent.setType("text/plain");
		startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
	}

	public int deIdentificadorAFila(int id){
		for(int i = 0; i <Game.NFILAS;  i++)
			for (int j = 0 ; j < Game.NCOLUMNAS;  j++)
				if(ids[i][j] == id)
					return i;	
		return -1;
	}
	public int deIdentificadorAColumna(int id){
		for(int i = 0; i <Game.NFILAS;  i++)
			for (int j = 0 ; j < Game.NCOLUMNAS;  j++)
				if(ids[i][j] == id)
					return j;	
		return -1;
	}
	
	public void restart(){
		game = new Game();
	}
}