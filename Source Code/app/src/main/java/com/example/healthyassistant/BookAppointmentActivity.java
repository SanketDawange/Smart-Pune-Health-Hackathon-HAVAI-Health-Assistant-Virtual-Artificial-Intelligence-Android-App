package com.example.healthyassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;


//
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.speech.tts.TextToSpeech;
//

public class BookAppointmentActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    ImageButton imageButton;
    ListView messageList;
    EditText editText;
    int count =0;
    SpeechRecognizer speechRecognizer;

    private RequestQueue requestQueue;
    private List<String> messages;
    private ArrayAdapter<String> adapter;

    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_chat);

        messages = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);

        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i !=TextToSpeech.ERROR)
                    t1.setLanguage(Locale.ENGLISH);
            }
        });

        messageList = findViewById(R.id.messageList);
        messageList.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(this);

        // to hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        imageButton=findViewById(R.id.button);
        editText=findViewById(R.id.editText);

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);

        }

        speechRecognizer=SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count==0)
                {
                    imageButton.setImageDrawable(getDrawable(R.drawable.mic_icon));
                    speechRecognizer.startListening(speechRecognizerIntent);
                    count=1;
                }
                else {
                    imageButton.setImageDrawable(getDrawable(R.drawable.baseline_mic_off_24));
                    speechRecognizer.stopListening();
                    count=0;
                }
            }
        });

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                sendMessage(data.get(0));
                editText.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

    }



    // ------------------------------- ---------------------------------------------
    private void sendMessage(String userMessage) {
        String apiUrl = "https://havai.up.railway.app/makeAppointment/";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl + userMessage,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        messages.add("You: " + userMessage);
                        try{
                            String response2 = response.getString("response");
                            String number = response2.toString();

                                messages.add("Havai: "+response2.toString());
                                t1.speak(response2.toString(), TextToSpeech.QUEUE_FLUSH,null);
                                adapter.notifyDataSetChanged();
                            if(number.length()==10){
                                messages.add("Havai: lets have a call sir");
                                t1.speak(" lets have a call sir", TextToSpeech.QUEUE_FLUSH,null);
                                adapter.notifyDataSetChanged();
                                makePhoneCall(number);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        messages.add("Error: " + error.getMessage());
                        adapter.notifyDataSetChanged();
                    }
                }
        );

        requestQueue.add(request);
    }
    // ------------------------------- ---------------------------------------------
    private void makePhoneCall(String number) {
        //  String number = mEditTextNumber.getText().toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(BookAppointmentActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(BookAppointmentActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(BookAppointmentActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall("9359725515");
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

