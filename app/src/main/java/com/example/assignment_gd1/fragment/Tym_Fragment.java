package com.example.assignment_gd1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment_gd1.R;
import com.example.assignment_gd1.adapter.GiohangAdapter;
import com.example.assignment_gd1.adapter.SpMuaAdapter;
import com.example.assignment_gd1.api.Api_Giohang;
import com.example.assignment_gd1.api.Api_SPmua;
import com.example.assignment_gd1.model.GioHang;
import com.example.assignment_gd1.model.ReceGiohang;
import com.example.assignment_gd1.model.ReceSpMua;
import com.example.assignment_gd1.model.SPMua;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Tym_Fragment extends Fragment {

    RecyclerView rcv;
    ArrayList<SPMua> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tym_, container, false);

        rcv = view.findViewById(R.id.rcv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        list = new ArrayList<>();

        hienthiDanhsach();


        return view;
    }
    private void hienthiDanhsach() {

        Api_SPmua.apiSPmua.getDanhsach().enqueue(new Callback<ReceSpMua>() {
            @Override
            public void onResponse(Call<ReceSpMua> call, Response<ReceSpMua> response) {
                list = response.body().getData();
                SpMuaAdapter adapter = new SpMuaAdapter(list,getContext());
                rcv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ReceSpMua> call, Throwable t) {

            }
        });
    }

}