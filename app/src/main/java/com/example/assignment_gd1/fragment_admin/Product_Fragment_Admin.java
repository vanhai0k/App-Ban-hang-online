package com.example.assignment_gd1.fragment_admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_gd1.R;
import com.example.assignment_gd1.api.Api_Product;
import com.example.assignment_gd1.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product_Fragment_Admin extends Fragment {

    EditText ed_title_pro,ed_linkanh_pro,ed_infomation_pro,ed_phongcach_pro,ed_xuatsu_pro,ed_size_pro,ed_soluong_pro,ed_price_pro;
    Button btn_addpro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_2, container, false);

        ed_linkanh_pro = view.findViewById(R.id.ed_linkanh_pro);
        ed_infomation_pro = view.findViewById(R.id.ed_infomation_pro);
        ed_price_pro = view.findViewById(R.id.ed_price_pro);
        ed_size_pro = view.findViewById(R.id.ed_size_pro);
        ed_title_pro = view.findViewById(R.id.ed_title_pro);
        ed_phongcach_pro = view.findViewById(R.id.ed_phongcach_pro);
        ed_xuatsu_pro = view.findViewById(R.id.ed_xuatsu_pro);
        ed_soluong_pro = view.findViewById(R.id.ed_soluong_pro);
        btn_addpro = view.findViewById(R.id.btn_addpro);


        btn_addpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postProduct();
            }
        });

        return view;
    }

    private void postProduct() {
        String title = ed_title_pro.getText().toString().trim();
        double price = Double.parseDouble(ed_price_pro.getText().toString().trim());
        int soluong = Integer.parseInt(ed_soluong_pro.getText().toString().trim());
        String size = ed_size_pro.getText().toString().trim();
        String xuatsu = ed_xuatsu_pro.getText().toString().trim();
        String phongcach = ed_phongcach_pro.getText().toString().trim();
        String infomation = ed_infomation_pro.getText().toString().trim();
        String image = ed_linkanh_pro.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(String.valueOf(price)) || TextUtils.isEmpty(String.valueOf(soluong)) || TextUtils.isEmpty(size) || TextUtils.isEmpty(xuatsu) ||
                TextUtils.isEmpty(phongcach) || TextUtils.isEmpty(infomation) || TextUtils.isEmpty(image)){
            Toast.makeText(getContext(), "Vui long khong de trong", Toast.LENGTH_SHORT).show();
            return;
        }
        Api_Product.apiProduct.postProduct(new Product(image,title,price,soluong,size,xuatsu,phongcach,infomation))
                .enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}