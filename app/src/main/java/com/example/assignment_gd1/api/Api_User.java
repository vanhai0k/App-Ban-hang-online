package com.example.assignment_gd1.api;

import com.example.assignment_gd1.Service.UserResponse;
import com.example.assignment_gd1.model.ReceSpMua;
import com.example.assignment_gd1.model.ReceUser;
import com.example.assignment_gd1.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api_User {


    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();

    Api_User apiUser = new Retrofit.Builder()
            .baseUrl("http://192.168.1.11:3000/api/")
//            .baseUrl("http://10.24.29.207:3000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api_User.class);
//    @FormUrlEncoded
    @GET("user")
    Call<ReceUser> getDanhsach();

    @DELETE("user/delete/{id}")
    Call<User> deleteUser(@Path("id") String id);

    @PUT("user/update/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);

    @GET("user")
    Call<UserResponse> login(
            @Query("username") String username,
            @Query("passwd") String passwd
    );
}
