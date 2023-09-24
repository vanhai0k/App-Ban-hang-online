package com.example.assignment_gd1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.assignment_gd1.Infomation_Pro;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.adapter.ProducrAdapter;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.model.Comment;
import com.example.assignment_gd1.model.Product;
import com.example.assignment_gd1.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Product_Fragment extends Fragment {

    RecyclerView rcv;
    List<Product> list;
    ProducrAdapter adapter;
    private TextView tv_iduser;
    ImageView im_imageuser;
    private String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_, container, false);

        rcv = view.findViewById(R.id.rcv);
        tv_iduser = view.findViewById(R.id.iduser_nd);
        im_imageuser = view.findViewById(R.id.im_imageuser);

        list = new ArrayList<>();
        Bundle argument = getArguments();
        if (argument != null) {
            String id = argument.getString("id");
            String image = argument.getString("image");
            tv_iduser.setText(id);
            Glide.with(getContext())
                    .load(image)
                    .into(im_imageuser);
//            Log.i("id_pragment", id);
        }



        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rcv.setLayoutManager(layoutManager);
        hienthiDanhsachPro();
        return view;
    }
    private  void hienthiDanhsachPro(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(API.API_GET, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Product product = new Product();
                        product.set_id(jsonObject.getString("_id"));
                        product.setImage(jsonObject.getString("image"));
                        product.setTitle(jsonObject.getString("title"));
                        product.setPrice(jsonObject.getDouble("price"));
                        product.setQuantity(jsonObject.getInt("quantity"));
                        product.setInfomation(jsonObject.getString("infomation"));
                        product.setPhongcach(jsonObject.getString("phongcach"));
                        product.setSize(jsonObject.getString("size"));


                        list.add(product);
                    }
                    adapter = new ProducrAdapter(list,getContext());
                    rcv.setAdapter(adapter);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}