package com.example.assignment_gd1.api;

import com.example.assignment_gd1.model.GioHang;
import com.example.assignment_gd1.model.ReceGiohang;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api_Giohang {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();

    Api_Giohang apiGiohang = new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:3000/api/")
//            .baseUrl("http://10.24.45.15:3000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api_Giohang.class);

    @GET("giohang")
    Call<ReceGiohang> getDanhsach();

    @DELETE("giohang/delete/{id}")
    Call<GioHang> deleteGiohang(@Path("id") String id);
}
