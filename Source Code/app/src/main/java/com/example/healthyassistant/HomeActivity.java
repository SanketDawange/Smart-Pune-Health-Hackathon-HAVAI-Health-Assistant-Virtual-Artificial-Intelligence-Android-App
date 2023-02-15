package com.example.healthyassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {


    Button chat_button, mic_button, book_appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // to hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        chat_button = (Button) findViewById(R.id.chat_button);
        mic_button = (Button) findViewById(R.id.mic_button);
        book_appointment = (Button) findViewById(R.id.book_appointment);
        Button scheme = findViewById(R.id.scheme);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome, "+getIntent().getStringExtra("USERNAME"));

        chat_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(intent);
            }
        });

        // to open the voice chat
        mic_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VoiceChatActivity.class);
                startActivity(intent);
            }
        });

        book_appointment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookAppointmentActivity.class);
                startActivity(intent);
            }
        });
        scheme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SchemesActivity.class);
                startActivity(intent);
            }
        });
    }
}