package  com.example.healthyassistant;
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
public class ChatActivity extends AppCompatActivity {

    private EditText inputMessage;
    private Button sendButton;
    private ListView messageList;
    private RequestQueue requestQueue;
    private List<String> messages;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // to hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        inputMessage = findViewById(R.id.inputMessage);
        sendButton = findViewById(R.id.sendButton);
        messageList = findViewById(R.id.messageList);

        messages = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        messageList.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String userMessage = inputMessage.getText().toString();
        inputMessage.setText("");
        String apiUrl = "https://havai.up.railway.app/getResponse/";
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
                            messages.add("Havai: " + response2.toString());
                            adapter.notifyDataSetChanged();
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
}