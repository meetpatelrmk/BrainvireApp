package com.nirmit.brainvireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        getSupportActionBar().hide();
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
         @Override
         public void run() {

             if (preferences.contains("isUserLogin")) {
                 Intent intent = new Intent(SplashActivity.this, DataActivity.class);
                 startActivity(intent);
             } else {
                 Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                 startActivity(intent);
             }
         }
     },2000);

    }
}