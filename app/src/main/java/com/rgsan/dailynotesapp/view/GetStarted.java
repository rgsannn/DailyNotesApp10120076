package com.rgsan.dailynotesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.rgsan.dailynotesapp.R;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class GetStarted extends AppCompatActivity {
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        getSupportActionBar().hide();
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(view -> {
            Intent i = new Intent(GetStarted.this, MainActivity.class);
            startActivity(i);
            finish();
        });
    }
}
