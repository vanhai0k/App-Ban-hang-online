package com.example.assignment_gd1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comment_Product_Admin extends AppCompatActivity {

    RecyclerView rcv_comment;
    List<Comment> list;
    EditText ed_comment,ed_idpro,ed_iduser;
    Button btn_comment;
    ImageView img_close;
    CommentAdminAdapter adaptercm;
    CommentService commentService;
    private static final String TAG = "aaaaaaaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_product_admin);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.8:3000/api/") // Update with your API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commentService = retrofit.create(CommentService.class);


        rcv_comment = findViewById(R.id.rcv_comment);
        ed_idpro = findViewById(R.id.ed_idpro);
        ed_iduser = findViewById(R.id.ed_iduser);
        img_close = findViewById(R.id.img_close);



        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rcv_comment.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getBaseContext(),DividerItemDecoration.VERTICAL);
        rcv_comment.addItemDecoration(itemDecoration);


        Bundle bundle = getIntent().getExtras();

        ed_idpro.setText(bundle.getString("_id"));
        ed_iduser.setText("64c73fe4eacc28222a7d3d53");

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        list = new ArrayList<>();

        hienthiComment();

    }
    private boolean isValied(String id_user, String comment) {
        if (id_user.isEmpty()){
            showMessage("Please enter user id_user");
            return false;
        }
        if (comment.isEmpty()){
            showMessage("Please enter comment");
            return false;
        }
        return true;
    }
    private void showMessage(String msg) {
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
    }
    private void hienthiComment() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.API_GetComment , null, new com.android.volley.Response.Listener<JSONObject>() {
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
                            comment.setUsername(jsonObjectUser.getString("username"));
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        list.add(comment);
                    }
                    adaptercm = new CommentAdminAdapter(list,getBaseContext());
                    rcv_comment.setAdapter(adaptercm);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Call API Fail", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}