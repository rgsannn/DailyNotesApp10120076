package com.rgsan.dailynotesapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rgsan.dailynotesapp.R;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}