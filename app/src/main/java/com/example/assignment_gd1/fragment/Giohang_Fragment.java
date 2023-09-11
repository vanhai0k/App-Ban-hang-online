package com.example.assignment_gd1.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.adapter.CommentAdminAdapter;
import com.example.assignment_gd1.adapter.GiohangAdapter;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.api.Api_Giohang;
import com.example.assignment_gd1.model.Comment;
import com.example.assignment_gd1.model.GioHang;
import com.example.assignment_gd1.model.ReceGiohang;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Giohang_Fragment extends Fragment {

    RecyclerView rcv;
    ArrayList<GioHang> list;
    GiohangAdapter adapter;
    TextView iduser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_giohang_, container, false);

        rcv = view.findViewById(R.id.rcv);
        iduser = view.findViewById(R.id.iduser);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        list = new ArrayList<>();

//        iduser.setText(curid);

//        hienthiDanhsach();
        giohang1Pro();
        return view;
    }

    private void hienthiDanhsach() {

        Api_Giohang.apiGiohang.getDanhsach().enqueue(new Callback<ReceGiohang>() {
            @Override
            public void onResponse(Call<ReceGiohang> call, Response<ReceGiohang> response) {
                list = response.body().getData();
                GiohangAdapter adapter = new GiohangAdapter(list,getContext());
                rcv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ReceGiohang> call, Throwable t) {

            }
        });
    }
    private void giohang1Pro (){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("luuduser", Context.MODE_PRIVATE);
        String curid = sharedPreferences.getString("idusers", null);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.API_Get_Commentgiohang1pro + curid, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        GioHang jh = new GioHang();
                        jh.setId(jsonObject.getString("_id"));
                        jh.setImage(jsonObject.getString("image"));
                        jh.setTitle(jsonObject.getString("title"));
                        jh.setPricegh(jsonObject.getDouble("pricegh"));
                        jh.setQuantity(jsonObject.getInt("quantity"));
                        jh.setThanhtien(jsonObject.getDouble("thanhtien"));
                        jh.setDate(jsonObject.getString("date"));


                        list.add(jh);
                    }
                    adapter = new GiohangAdapter(list,getContext());
                    rcv.setAdapter(adapter);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}