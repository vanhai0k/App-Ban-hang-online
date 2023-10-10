package com.example.assignment_gd1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment_gd1.adapter.CommentAdapter;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.model.Comment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comment_Product extends AppCompatActivity {

    RecyclerView rcv_comment;
    List<Comment> list;
    EditText ed_comment,ed_idpro,ed_iduser,ed_idusertest;
    Button btn_comment;
    ImageView img_close;
    CommentAdapter adapter;
    CommentService commentService;
    private static final String TAG = "aaaaaaaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_product);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:3000/api/") // Update with your API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commentService = retrofit.create(CommentService.class);

        rcv_comment = findViewById(R.id.rcv_comment);
        ed_comment = findViewById(R.id.ed_commentsp);
        ed_idpro = findViewById(R.id.ed_idpro);
        ed_iduser = findViewById(R.id.ed_iduser);
        btn_comment = findViewById(R.id.btn_comment);
        img_close = findViewById(R.id.img_close);
        ed_idusertest = findViewById(R.id.ed_idusertest);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rcv_comment.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getBaseContext(),DividerItemDecoration.VERTICAL);
        rcv_comment.addItemDecoration(itemDecoration);


        Bundle bundle = getIntent().getExtras();

        ed_idpro.setText(bundle.getString("_id"));

        SharedPreferences sharedPreferences = getSharedPreferences("luuduser", MODE_PRIVATE);
        String curid = sharedPreferences.getString("idusers", null);
        ed_iduser.setText(curid);


        list = new ArrayList<>();
        hienthiComment();
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id_user = ed_iduser.getText().toString().trim();
                String comment = ed_comment.getText().toString().trim();
                if (isValied(id_user,comment,v)){
                    addComment();

                }
            }
        });

    }
    private boolean isValied(String id_user, String comment, View v) {
        if (id_user.isEmpty()){
            showMessage("Please enter user id_user");
            return false;
        }
        if (comment.isEmpty()){
//            showMessage("Hãy viết dánh giá của bạn");
            showThongbao(v);
            return false;
        }
        return true;
    }
    private void showMessage(String msg) {
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
    }
    private void addComment() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String comment = ed_comment.getText().toString().trim();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id_product",ed_idpro.getText().toString().trim());
            jsonObject.put("id_user",ed_iduser.getText().toString().trim());
            jsonObject.put("comment",ed_comment.getText().toString().trim());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API.API_PostComment, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(Comment_Product.this, "Da comment bai viet", Toast.LENGTH_SHORT).show();
                    ed_comment.setText("");
                    list.clear();
                    hienthiComment();
                    adapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Comment_Product.this, "Fail roi", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(jsonObjectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void hienthiComment() {


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.API_GetComment + ed_idpro.getText().toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Comment comment = new Comment();
                        comment.setComment(jsonObject.getString("comment"));
                        comment.setId(jsonObject.getString("_id"));

                        try {
                            JSONObject jsonObjectpro = jsonObject.getJSONObject("id_product");
                            comment.setTitle(jsonObjectpro.getString("title"));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        try {
                            JSONObject jsonObjectUser = jsonObject.getJSONObject("id_user");
                                comment.setId_user(jsonObjectUser.getString("_id"));
                                comment.setUsername(jsonObjectUser.getString("username"));
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        list.add(comment);
                    }
                    adapter = new CommentAdapter(list,getBaseContext());
                    rcv_comment.setAdapter(adapter);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Comment_Product.this, "Call API Fail", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void showThongbao (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_thongbao,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        ImageView close = view.findViewById(R.id.close);
        TextView tvtext = view.findViewById(R.id.tvtext);
        tvtext.setText("Hãy viết đánh giá của bạn!!!");
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}