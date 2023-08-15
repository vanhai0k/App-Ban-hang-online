package com.example.assignment_gd1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment_gd1.fragment_admin.Home_Fragment_Admin;
import com.example.assignment_gd1.fragment_admin.Product_Fragment_Admin;
import com.example.assignment_gd1.fragment_admin.Setting_Admin_Fragment;
import com.example.assignment_gd1.fragment_admin.User_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GiaoDien_Admin_Activity extends AppCompatActivity {


    BottomNavigationView navigationView;
    private String username,email,phanquyen,image,iduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_admin);



        Intent intent = getIntent();
        if (intent != null) {

            iduser = intent.getStringExtra("id");
            username = intent.getStringExtra("username");
            email = intent.getStringExtra("email");
            phanquyen = intent.getStringExtra("phanquyen");
            image = intent.getStringExtra("image");
        }


        Bundle bundle = new Bundle();
        bundle.putString("id", iduser);
        bundle.putString("username", username);
        bundle.putString("email", email);
        bundle.putString("image", image);
        bundle.putString("phanquyen", phanquyen);

//
        Home_Fragment_Admin homeFragmentAdmin = new Home_Fragment_Admin();
        homeFragmentAdmin.setArguments(bundle);

        Setting_Admin_Fragment settingAdminFragment = new Setting_Admin_Fragment();
        settingAdminFragment.setArguments(bundle);

        Product_Fragment_Admin productFragmentAdmin = new Product_Fragment_Admin();
        productFragmentAdmin.setArguments(bundle);

        User_Fragment userFragment = new User_Fragment();
        userFragment.setArguments(bundle);


        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber_admin, new Home_Fragment_Admin()).commit();
        navigationView.setSelectedItemId(R.id.nav_home_admin);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_home_admin) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber_admin,homeFragmentAdmin).commit();
                } else if (item.getItemId() == R.id.nav_user_admin) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber_admin,userFragment).commit();
                }else if (item.getItemId() == R.id.nav_addpro_admin) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber_admin,productFragmentAdmin).commit();
                }else if (item.getItemId() == R.id.nav_setting_admin) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber_admin,settingAdminFragment).commit();
                }

                return true;
            }
        });

    }
}