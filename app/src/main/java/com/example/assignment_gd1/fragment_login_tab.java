package com.example.assignment_gd1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_gd1.api.Api_User;
import com.example.assignment_gd1.fragment.Setting_Fragment;
import com.example.assignment_gd1.model.ReceUser;
import com.example.assignment_gd1.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fragment_login_tab extends Fragment {

    TextView tv_phanquyen;
    EditText ed_pass,ed_username;
    Button login_button;
    ArrayList<User> list;
    User mUser;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);



        ed_username = view.findViewById(R.id.tv_username);
        ed_pass = view.findViewById(R.id.tv_passw);
        login_button = view.findViewById(R.id.login_button);
        tv_phanquyen = view.findViewById(R.id.tv_phanquyen);


        sharedPreferences = getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        list = new ArrayList<>();
        getListUser();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickLogin();
            }
        });

        return view;
    }

    private void clickLogin() {
        String username = ed_username.getText().toString().trim();
        String password = ed_pass.getText().toString().trim();


        if (list == null || list.isEmpty()){
            return;
        }

        boolean isHasUser = false;
        for (User user : list){
            if (username.equalsIgnoreCase(user.getUsername()) && password.equalsIgnoreCase(user.getPasswd())){
                mUser = user;

                isHasUser = true;
                break;
            }
        }

        if (isHasUser){

            SharedPreferences preferences = getContext().getSharedPreferences("luuduser",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = preferences.edit();
            editor1.putString("idusers", mUser.getId());
            editor1.apply();

            // gui qua Infomation_User
            SharedPreferences preferences1 = getContext().getSharedPreferences("infoUser",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor2 = preferences1.edit();
            editor2.putString("iduser", mUser.getId());
            editor2.putString("username", mUser.getUsername());
            editor2.putString("email", mUser.getEmail());
            editor2.putString("image", mUser.getImage());
            editor2.putString("phone", mUser.getPhone());
            editor2.putString("gioitinh", mUser.getGioitinh());
            editor2.putString("ngaysinh", mUser.getNgaysinh());
            editor2.putString("passwd", mUser.getPasswd());
            editor2.putString("phanquyen", mUser.getPhanquyen());

            editor2.apply();


                if (mUser.getPhanquyen().equalsIgnoreCase("Admin")){
                    Intent i = new Intent(getContext(), GiaoDien_Admin_Activity.class);
//                    dulieudn();
                    i.putExtra("id", mUser.getId());
                    i.putExtra("username", mUser.getUsername());
                    i.putExtra("email", mUser.getEmail());
                    i.putExtra("image", mUser.getImage());
                    i.putExtra("phanquyen", mUser.getPhanquyen());

                    Log.i( "clickLogin: ", mUser.getId());

                    startActivity(i);
                }else{
                    Intent i = new Intent(getContext(),Giaodien_Activity.class);

                    i.putExtra("id", mUser.getId());
                    i.putExtra("username", mUser.getUsername());
                    i.putExtra("email", mUser.getEmail());
                    i.putExtra("image", mUser.getImage());
                    i.putExtra("phanquyen", mUser.getPhanquyen());

                    startActivity(i);
                }
        }else{
            Toast.makeText(getContext(), "Vui lòng kiểm tra lại email và password", Toast.LENGTH_SHORT).show();
        }

    }

    private void dulieudn() {
        editor = sharedPreferences.edit();

        editor.putString("id", mUser.getId());
        editor.putString("username", mUser.getUsername());
        editor.putString("email",mUser.getEmail());
        editor.putString("phanquyen",mUser.getPhanquyen());
        editor.putString("image",mUser.getImage());

        editor.commit();
    }

    private void getListUser() {
        Api_User.apiUser.getDanhsach().enqueue(new Callback<ReceUser>() {
            @Override
            public void onResponse(Call<ReceUser> call, Response<ReceUser> response) {
                list = response.body().getData();
            }

            @Override
            public void onFailure(Call<ReceUser> call, Throwable t) {

            }
        });
    }

    private void showMessage(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
    private boolean isValied(String name, String password, String email) {
        if (name.isEmpty()){
            showMessage("Please enter user name");
            return false;
        }
        if (password.isEmpty()){
            showMessage("Please enter password");
            return false;
        }
        return true;
    }


}