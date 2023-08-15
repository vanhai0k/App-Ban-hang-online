package com.example.assignment_gd1.fragment_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.adapter.UserDapter;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.api.Api_User;
import com.example.assignment_gd1.model.ReceUser;
import com.example.assignment_gd1.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class User_Fragment extends Fragment {

    List<User> list;
    UserDapter adapter;
    RecyclerView rcv_user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_, container, false);

        rcv_user = view.findViewById(R.id.rcv_user);


        rcv_user.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcv_user.addItemDecoration(itemDecoration);
        list = new ArrayList<>();

        hienthidanhsachRetrofit();

        return view;
    }
    private void hienthidanhsachRetrofit(){
        Api_User.apiUser.getDanhsach().enqueue(new Callback<ReceUser>() {
            @Override
            public void onResponse(Call<ReceUser> call, retrofit2.Response<ReceUser> response) {
                list = response.body().getData();
                adapter = new UserDapter(list,getContext());
                rcv_user.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ReceUser> call, Throwable t) {

            }
        });
    }
}