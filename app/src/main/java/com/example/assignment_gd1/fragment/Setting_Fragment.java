package com.example.assignment_gd1.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignment_gd1.MainActivity;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.api.Api_SPmua;
import com.example.assignment_gd1.model.ReceSpMua;
import com.example.assignment_gd1.model.SPMua;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Setting_Fragment extends Fragment {

    SharedPreferences sharedPreferences;
    ImageView im_image;
    private TextView tv_username_admin,tv_email_admin,tv_phanquyen,tv_thongke;
    String username,email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting_, container, false);

        tv_email_admin = view.findViewById(R.id.tv_email_admin);
        tv_username_admin = view.findViewById(R.id.tv_username_admin);
        tv_phanquyen = view.findViewById(R.id.tv_phanquyen);
        im_image = view.findViewById(R.id.im_image);
        Button btndangxuat = view.findViewById(R.id.btndangxuat);

        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);

                startActivity(intent);
            }
        });


        Bundle arguments = getArguments();
        if (arguments != null) {
            String username = arguments.getString("username");
            String email = arguments.getString("email");
            String phanquyen = arguments.getString("phanquyen");
            String image = arguments.getString("image");

            tv_username_admin.setText(username);
            tv_email_admin.setText(email);

            Glide.with(getContext())
                    .load(image)
                    .into(im_image);
            tv_phanquyen.setText(phanquyen);
        }


        return view;
    }
}