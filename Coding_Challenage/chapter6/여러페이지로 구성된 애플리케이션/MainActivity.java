package com.example.chapter6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIntro = findViewById(R.id.btnIntroduction);
        Button btnSettings = findViewById(R.id.btnSettings);
        Button btnStart = findViewById(R.id.btnStart);

        btnIntro.setOnClickListener(v -> {
            startActivity(new Intent(this, introductionActivity.class));
        });

        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        btnStart.setOnClickListener(v -> {
            startActivity(new Intent(this, StartActivity.class));
        });
    }
}
