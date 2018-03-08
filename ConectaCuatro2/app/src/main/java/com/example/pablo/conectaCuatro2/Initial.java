package com.example.pablo.conectaCuatro2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pablo.conectaCuatro2.R;

public class Initial extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial);
        ImageView img = (ImageView) findViewById(R.id.initial_img);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.initial);
        img.startAnimation(animation);
        Button online = (Button) findViewById(R.id.btOnline);
        Button offline = (Button) findViewById(R.id.btOffline);
        online.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Initial.this,interfazOnline.class));
            }
        });
        offline.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Initial.this,MainActivity.class));
            }
        });
    }

}
