package com.example.assignment_gd1;

import com.example.assignment_gd1.api.Api_Product;
import com.example.assignment_gd1.model.Comment;
import com.example.assignment_gd1.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommentService {


    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();

    CommentService commentService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:3000/api/")
//            .baseUrl("http:/10.24.30.110:3000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CommentService.class);

    @DELETE("comment/delete/{id}")
    Call<Comment> deleteComment(@Path("id") String id);
    @PUT("comment/update/{id}")
    Call<Comment> updateComment(@Path("id") String id, @Body Comment comment);

}
