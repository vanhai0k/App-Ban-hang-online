package com.example.assignment_gd1.api;

import com.example.assignment_gd1.model.ReceSpMua;
import com.example.assignment_gd1.model.SPMua;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitThongke {

    private static Retrofit retrofit;

    public static synchronized Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://10.24.31.110:3000/api/spmua")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static List<SPMua> getProducts() {
        try {
            // Create a Retrofit client
            Retrofit retrofit = getClient();

            // Create an instance of the API interface
            Api_SPmua api = retrofit.create(Api_SPmua.class);

            // Call the `getProducts()` method to fetch the products
            Call<ReceSpMua> call = api.getDanhsach();

            // Execute the call
            Response<ReceSpMua> response = call.execute();

            // Get the products from the response
            ReceSpMua spMuas = response.body();

            // Return the products
            return (List<SPMua>) spMuas;
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return null;
        }
    }
}
