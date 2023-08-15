package com.example.assignment_gd1.model;

import static com.example.assignment_gd1.api.RetrofitThongke.getClient;

import com.example.assignment_gd1.api.Api_SPmua;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReceSpMua {

    @SerializedName("data")
    private ArrayList<SPMua> data;
    private String msg;

    private static Retrofit retrofit;

    public static synchronized Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://192.168.1.8:3000/api/spmua")
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<SPMua> getData() {
        return data;
    }

    public void setData(ArrayList<SPMua> data) {
        this.data = data;
    }
}
