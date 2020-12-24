package com.nirmit.brainvireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
         @Override
         public void run() {
            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
             Log.d("spaslh", "run: avyu spaslsh");
            finish();
         }
     },2000);

    }
}