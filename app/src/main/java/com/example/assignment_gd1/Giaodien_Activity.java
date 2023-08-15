package com.example.assignment_gd1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.assignment_gd1.fragment.Giohang_Fragment;
import com.example.assignment_gd1.fragment.Product_Fragment;
import com.example.assignment_gd1.fragment.Setting_Fragment;
import com.example.assignment_gd1.fragment.Tym_Fragment;
import com.example.assignment_gd1.fragment_admin.Home_Fragment_Admin;
import com.example.assignment_gd1.fragment_admin.Product_Fragment_Admin;
import com.example.assignment_gd1.fragment_admin.Setting_Admin_Fragment;
import com.example.assignment_gd1.fragment_admin.User_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Giaodien_Activity extends AppCompatActivity {

    BottomNavigationView navigationView;
    private String username,email,phanquyen,image,iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


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

        Log.i( "iduser: ", iduser);

        Product_Fragment productFragment = new Product_Fragment();
        productFragment.setArguments(bundle);

        Setting_Fragment settingFragment = new Setting_Fragment();
        settingFragment.setArguments(bundle);

        Giohang_Fragment giohangFragment = new Giohang_Fragment();
        giohangFragment.setArguments(bundle);

        Tym_Fragment spmua = new Tym_Fragment();
        spmua.setArguments(bundle);


        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber, new Product_Fragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber,productFragment).commit();

                }else if (item.getItemId() == R.id.nav_giohang) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber,giohangFragment).commit();

                }else if (item.getItemId() == R.id.nav_tym) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber,spmua).commit();

                }else if (item.getItemId() == R.id.nav_setting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_contaiber,settingFragment).commit();

                }
                return true;
            }
        });
    }
}