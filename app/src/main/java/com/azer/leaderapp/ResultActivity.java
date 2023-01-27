package com.azer.leaderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_result);
            TextView txt = findViewById(R.id.result_text);
            ImageView back = findViewById(R.id.result_back);
            Button btn = findViewById(R.id.result_done);
            back.setOnClickListener(view -> {
                this.finish();
            });
            btn.setOnClickListener(view -> {
                this.finish();
            });

            txt.setText(this.getIntent().getStringExtra("result"));
        }
        catch (Exception exception)
        {
            this.finish();
        }
    }


}