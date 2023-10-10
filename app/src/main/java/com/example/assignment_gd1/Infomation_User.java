package com.example.assignment_gd1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.assignment_gd1.adapter.UserDapter;
import com.example.assignment_gd1.api.Api_User;
import com.example.assignment_gd1.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Infomation_User extends AppCompatActivity {

    ImageView back;
    TextInputEditText name,email,phone,gioitinh,ngaysinh;
    ImageView image;
    LinearLayout capnhapUser;
    String curid,curpasswd,curphanquyen;
    List<User> list;
    UserDapter dapter;
    Spinner genderSpinner;
    String[] genders = {"Nam", "Nữ", "Khac"};
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation_user);
        anhxa();

        SharedPreferences sharedPreferences = getSharedPreferences("infoUser", MODE_PRIVATE);
        curid = sharedPreferences.getString("iduser", null);
        String curngaysinh = sharedPreferences.getString("ngaysinh", null);
        String curgioitinh = sharedPreferences.getString("gioitinh", null);
        String curphone = sharedPreferences.getString("phone", null);
        String curimage = sharedPreferences.getString("image", null);
        String curemail = sharedPreferences.getString("email", null);
        String curusername = sharedPreferences.getString("username", null);
        curpasswd = sharedPreferences.getString("passwd", null);
        curphanquyen = sharedPreferences.getString("phanquyen", null);


        list= new ArrayList<>();
        name.setText(curusername);
        email.setText(curemail);
        phone.setText(curphone);
        gioitinh.setText(curgioitinh);
        ngaysinh.setText(curngaysinh);
        Glide.with(getBaseContext())
                .load(curimage)
                .into(image);
    }

    private void anhxa() {
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        gioitinh = findViewById(R.id.gioitinh);
        ngaysinh = findViewById(R.id.ngaysinh);
        image = findViewById(R.id.img);
        capnhapUser = findViewById(R.id.capnhapUser);
        genderSpinner = findViewById(R.id.gender_spinner);


// Tạo một ArrayAdapter để hiển thị dữ liệu trong Spinner
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Thiết lập ArrayAdapter cho Spinner

        genderSpinner.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        capnhapUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateuser();
            }
        });
    }

    private void updateuser() {

        String nameuser = name.getText().toString().trim();
        String emailuser = email.getText().toString().trim();
        String phoneuser = phone.getText().toString().trim();
        String gioitinhuser = gioitinh.getText().toString().trim();
        String ngaysinhuser = ngaysinh.getText().toString().trim();

        String selectedGender = genderSpinner.getSelectedItem().toString();

        User user = new User();
        user.setUsername(nameuser);
        user.setEmail(emailuser);
        user.setPhone(phoneuser);
        user.setGioitinh(selectedGender);
        user.setNgaysinh(ngaysinhuser);
        user.setPhanquyen(curphanquyen);
        user.setPasswd(curpasswd);

        Api_User.apiUser.updateUser(curid,user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    list.clear();
                    User user = response.body();

                    list.add(user);
                    dapter = new UserDapter(list,getBaseContext());
                    Toast.makeText(Infomation_User.this, "Cap nhap thanh cong", Toast.LENGTH_SHORT).show();
                    dapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}