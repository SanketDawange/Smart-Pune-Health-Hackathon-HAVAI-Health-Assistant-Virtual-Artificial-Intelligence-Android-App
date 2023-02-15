package com.example.healthyassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DibateseMainACT extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dibatese_main);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                makePhoneCall("7083694063");
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                makePhoneCall("9359725515");
            }
        });
    }

    private void makePhoneCall(String number) {
        //  String number = mEditTextNumber.getText().toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(DibateseMainACT.this,
                    android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DibateseMainACT.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(DibateseMainACT.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

}