package com.example.healthyassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SchemesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button button1a = findViewById(R.id.button1a);
        Button button2a = findViewById(R.id.button2a);
        Button button3a = findViewById(R.id.button3a);
        Button button4a = findViewById(R.id.button4a);
        Button button5a = findViewById(R.id.button5a);
        button1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), D1_Kidney.class);
                startActivity(intent);
            }
        });
        button2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), D2_Diabetes.class);
                startActivity(intent);
            }
        });
        button3a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), D3_Heart.class);
                startActivity(intent);
            }
        });
        button4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), D4_Respiratory.class);
                startActivity(intent);
            }
        });
        button5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), D5_Cancer.class);
                startActivity(intent);
            }
        });


    }
}