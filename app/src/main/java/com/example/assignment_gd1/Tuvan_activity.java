package com.example.assignment_gd1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment_gd1.adapter.CommentAdminAdapter;
import com.example.assignment_gd1.adapter.MessageAdapter;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.model.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tuvan_activity extends AppCompatActivity {

    RecyclerView rcv;
    EditText ed_content;
    Button btn_content;
    ImageView back;
    MessageAdapter adapter;
    List<Message> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuvan);

        rcv = findViewById(R.id.rcv);
        ed_content = findViewById(R.id.ed_content);
        btn_content = findViewById(R.id.btn_content);
        back = findViewById(R.id.back);

        LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
        rcv.setLayoutManager(manager);
        list = new ArrayList<>();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("luuduser", MODE_PRIVATE);
        curid = sharedPreferences.getString("idusers", null);

        hienthimessage();

        btn_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

    }
    private String curid;

    private void hienthimessage() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.API_Get_Message + curid, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Message message = new Message();
                        message.setContent(jsonObject.getString("content"));

                        try {
                            JSONObject jsonObjectUser = jsonObject.getJSONObject("id_user");
                            message.setUsername(jsonObjectUser.getString("username"));
                            message.setImage(jsonObjectUser.getString("image"));
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        list.add(message);
                    }
                    adapter = new MessageAdapter(getBaseContext(),list);
                    rcv.setAdapter(adapter);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    private void sendMessage() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String send = ed_content.getText().toString().trim();
        if (send.isEmpty()){
            Toast.makeText(this, "Nhap tin nhan", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id_user",curid);
            jsonObject.put("content",send);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API.API_Send_Message, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    ed_content.setText("");
                    list.clear();
                    hienthimessage();
                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Tuvan_activity.this, "Fail roi", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }
}