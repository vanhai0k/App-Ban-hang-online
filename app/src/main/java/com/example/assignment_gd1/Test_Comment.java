package com.example.assignment_gd1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment_gd1.adapter.CommentAdminAdapter;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.model.Comment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Test_Comment extends AppCompatActivity {

    CommentAdminAdapter adminAdapter;
    EditText ed_comment,ed_idpro,ed_iduser;
    List<Comment> list;
    ImageView img_close;
    private static final String TAG = "aaaaaaaa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_comment);

        RecyclerView rcv = findViewById(R.id.rcv_commenttest);
        LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
        rcv.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getBaseContext(),DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);
        ed_idpro = findViewById(R.id.ed_idpro);
        ed_iduser = findViewById(R.id.ed_iduser);
        img_close = findViewById(R.id.img_close);

        list = new ArrayList<>();
//        adminAdapter = new CommentAdminAdapter();

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        String productId = getIntent().getStringExtra("_id");
        ed_idpro.setText(productId);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.API_Get_Comment + productId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Comment comment = new Comment();
                        comment.setComment(jsonObject.getString("comment"));

                        try {
                            JSONObject jsonObjectpro = jsonObject.getJSONObject("id_product");
                            comment.setTitle(jsonObjectpro.getString("title"));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        try {
                            JSONObject jsonObjectUser = jsonObject.getJSONObject("id_user");
                            comment.setUsername(jsonObjectUser.getString("username"));
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        list.add(comment);
                    }
                    adminAdapter = new CommentAdminAdapter(list,getBaseContext());
                    rcv.setAdapter(adminAdapter);


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
}