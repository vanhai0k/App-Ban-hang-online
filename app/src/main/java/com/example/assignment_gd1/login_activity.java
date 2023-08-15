package com.example.assignment_gd1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_gd1.Service.UserResponse;
import com.example.assignment_gd1.api.Api_User;
import com.example.assignment_gd1.model.ReceUser;
import com.example.assignment_gd1.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_activity extends AppCompatActivity {

    TextView tv_phanquyen;
    EditText ed_pass,ed_username;
    Button login_button;
    ArrayList<User> list;
    User mUser;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_username = findViewById(R.id.tv_username);
        ed_pass = findViewById(R.id.tv_passw);
        login_button = findViewById(R.id.login_button);
        tv_phanquyen = findViewById(R.id.tv_phanquyen);

        list = new ArrayList<>();
        getListUser();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed_username.getText().toString().trim();
                String password = ed_pass.getText().toString().trim();
//                clickLogin();
                login(username, password);
            }
        });
    }

    private void login(String username, String password) {
        Call<UserResponse> call = Api_User.apiUser.login(username,password);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    if (userResponse != null && userResponse.getStatus() == 1) {
                        // Login successful
                        List<User> userList = userResponse.getData();
                        if (userList != null && userList.size() > 0) {
                            boolean isMatched = false;
                            User loggedInUser = null;
                            for (User user : userList) {
                                String apiUsername = user.getUsername();
                                String apiPassword = user.getPasswd();

                                // Compare the entered email and password with the API response
                                if (username.equals(apiUsername) && password.equals(apiPassword)) {
                                    // Username and password match
                                    isMatched = true;
                                    loggedInUser = user;
                                    break;
                                }
                            }

                            if (isMatched) {
                                // Username and password match
                                Toast.makeText(getBaseContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getBaseContext(), GiaoDien_Admin_Activity.class);

                                // Pass the user information as extras in the Intent
                                intent.putExtra("username", loggedInUser.getUsername());
                                intent.putExtra("email", loggedInUser.getEmail());

                                // Start the MainActivity
                                startActivity(intent);
                            } else {
                                // Incorrect username or password
                                Toast.makeText(getBaseContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        // Login failed
                        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // API request failed
                    Toast.makeText(getBaseContext(), "API request failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Handle network or API call failure
                Toast.makeText(getBaseContext(), "Login failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
            if (mUser.getPhanquyen().equalsIgnoreCase("Admin")){
                Intent i = new Intent(this,GiaoDien_Admin_Activity.class);

//                    dulieudn();
                i.putExtra("username", mUser.getUsername());
                i.putExtra("email", mUser.getEmail());

                Toast.makeText(getBaseContext(), "name"+ mUser.getUsername(), Toast.LENGTH_SHORT).show();

                startActivity(i);
            }else{
                Intent i = new Intent(getBaseContext(),Giaodien_Activity.class);
                startActivity(i);
            }
        }else{
            Toast.makeText(getBaseContext(), "Vui lòng kiểm tra lại email và password", Toast.LENGTH_SHORT).show();
        }

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
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
    }
}