package com.example.assignment_gd1.api;

import com.example.assignment_gd1.model.ReceSpMua;
import com.example.assignment_gd1.model.SPMua;
import com.example.assignment_gd1.model.Thongke;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api_Thongke {

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();

    Api_Thongke api_Thongke = new Retrofit.Builder()
            .baseUrl(API.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api_Thongke.class);

    @GET("/sumprice")
    Call<Thongke> getTotalAmount();
}
