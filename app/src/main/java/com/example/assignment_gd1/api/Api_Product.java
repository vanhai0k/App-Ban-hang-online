package com.example.assignment_gd1.api;

import com.example.assignment_gd1.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api_Product {

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyy").create();

    Api_Product apiProduct = new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:3000/api/")
//            .baseUrl("http:/10.24.30.110:3000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api_Product.class);

    @POST("users")
    Call<Product> postProduct(@Body Product product);

    @DELETE("users/delete/{id}")
    Call<Product> deleteProduct(@Path("id") String id);

    @PUT("users/update/{id}")
    Call<Product> updateProduct(@Path("id") String id, @Body Product product);
}
