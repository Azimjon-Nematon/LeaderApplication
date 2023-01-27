package com.azer.leaderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView test = findViewById(R.id.test);
        test.setOnClickListener(view -> {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        });

        CardView dev = findViewById(R.id.dev);
        dev.setOnClickListener(view -> {
            Intent intent = new Intent(this, DevelopmentActivity.class);
            startActivity(intent);
        });

        CardView other = findViewById(R.id.other);
        other.setOnClickListener(view -> {
            Intent intent = new Intent(this, LeadersActivity.class);
            startActivity(intent);
        });

    }
}