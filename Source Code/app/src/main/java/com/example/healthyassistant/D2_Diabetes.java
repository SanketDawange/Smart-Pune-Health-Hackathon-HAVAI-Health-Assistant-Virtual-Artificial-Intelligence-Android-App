package com.example.healthyassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class D2_Diabetes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d2_diabetes);
        Button dib_button = findViewById(R.id.dib_button);
        Button buttond4 = findViewById(R.id.buttond4);

        dib_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), requiredDocuments.class);
                startActivity(intent);
            }
        });
        buttond4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AyushManBharatACT.class);
                startActivity(intent);
            }
        });
    }
}