package com.example.weather_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



    public class SPLASH_SCREEN extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_splash_screen);
            TextView t=findViewById(R.id.textView2);
            Animation scale= AnimationUtils.loadAnimation(SPLASH_SCREEN.this,R.anim.scale);
            t.setAnimation(scale);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i=new Intent(SPLASH_SCREEN.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            },12000);

        }
    }

