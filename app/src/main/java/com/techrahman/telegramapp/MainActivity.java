package com.techrahman.telegramapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private EditText ed_name,ed_email,ed_complain;
    private Button btn;
    private String BOT_TOKEN = "7840495292:AAFDEQaPBpLWiatE0H7Vldpnq2DJJHmDecc";
    private String CHAT_ID = "1738427624";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_name = findViewById(R.id.etName);
        ed_email = findViewById(R.id.etEmailOrNumber);
        ed_complain = findViewById(R.id.etComplain);
        btn = findViewById(R.id.btnSubmit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_name.getText().toString();
                String email = ed_email.getText().toString();
                String complain = ed_complain.getText().toString();

                if (name.isEmpty() ||email.isEmpty() || complain.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                   String message = "Name: "+name+"\nEmail/Number: "+email+"\nComplain"+complain;
                    String encode = null;
                    try {
                        encode = URLEncoder.encode(message,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }

                    String url = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage?chat_id=" + CHAT_ID + "&text=" + encode;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);

                }

            }
        });



    }
}