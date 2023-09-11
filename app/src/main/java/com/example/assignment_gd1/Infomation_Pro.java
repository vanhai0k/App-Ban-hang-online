package com.example.assignment_gd1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.api.Api_SPmua;
import com.example.assignment_gd1.fragment.Giohang_Fragment;
import com.example.assignment_gd1.model.GioHang;
import com.example.assignment_gd1.model.Product;
import com.example.assignment_gd1.model.ReceSpMua;
import com.example.assignment_gd1.model.SPMua;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class Infomation_Pro extends AppCompatActivity {


    TextView tv_title,tv_mota,tv_price,tv_soluong,tv_binhluansanpham,tv_idtest,tv_idpro,iduser,
            tv_phongcach,tv_size,tv_soluongmua,tv_thanhtien,tv_linkanh,tvtt,tvttt;
    Button btn_themgiohang,btn_muasp;
    ImageView image,im_quaylai,img_tru,img_cong,im_vaogiohang;
    ProgressDialog progressDialog;
    RadioButton rdos,rdol,rdoxl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation_pro);

        anhxa();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng chờ...");
        btn_themgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGiohang();
            }
        });
        btn_muasp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muaSanPham();
            }
        });

        im_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getBaseContext(), Giaodien_Activity.class);
//                startActivity(i);
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        tv_idpro.setText(bundle.getString("id_pro"));
        tv_title.setText(bundle.getString("title"));
        tv_mota.setText(bundle.getString("infomation"));
        tv_phongcach.setText(bundle.getString("phongcach"));
        tv_size.setText(bundle.getString("size"));
        tv_linkanh.setText(bundle.getString("image"));

        tv_price.setText(bundle.getDouble("price")+"");
        tv_soluong.setText(bundle.getInt("quantity")+"");

        Glide.with(getBaseContext())
                .load(bundle.getString("image").toString())
                .into(image);

        SharedPreferences sharedPreferences = getSharedPreferences("luuduser", MODE_PRIVATE);
        String curid = sharedPreferences.getString("idusers", null);
        iduser.setText(curid);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("product")) {
            Product product = intent.getParcelableExtra("comic");
            tv_idtest.setText("Tác giả: "+product.getTitle());

        }


//        String idU = intent.getStringExtra("idUser");
//
//        tv_idtest.setText(idU);

        tv_binhluansanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idU=intent.getStringExtra("idUser");
            }
        });

        tv_thanhtien.setVisibility(View.INVISIBLE);

        double price_giohang = Double.parseDouble(tv_price.getText().toString().trim());
        img_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tv_thanhtien.setVisibility(View.VISIBLE);
                tvttt.setVisibility(View.VISIBLE);
                tvtt.setVisibility(View.VISIBLE);
                // Cập nhật văn bản của TextView
                tv_soluongmua.setText(String.valueOf(count));
                tv_thanhtien.setText(String.valueOf(price_giohang*count) );
            }
        });
        img_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    count--;
                    tv_thanhtien.setVisibility(View.VISIBLE);
                    tvttt.setVisibility(View.VISIBLE);
                    tvtt.setVisibility(View.VISIBLE);
                }
                if (count ==0){
                    tv_thanhtien.setText(tv_price.getText().toString().trim());
                }
                // Cập nhật văn bản của TextView
                tv_soluongmua.setText(String.valueOf(count));
                tv_thanhtien.setText(String.valueOf(price_giohang*count));
            }
        });
    }
    private int count = 0;

    private void anhxa() {
        tv_title = findViewById(R.id.tv_title);
        tv_idpro = findViewById(R.id.tv_id);
        iduser = findViewById(R.id.iduser);
        tv_mota = findViewById(R.id.tv_mota);
        tv_price = findViewById(R.id.tv_price);
        tv_soluong = findViewById(R.id.tv_soluong);
        tv_size = findViewById(R.id.tv_size);
        tv_phongcach= findViewById(R.id.tv_phongcach);
        image = findViewById(R.id.image);
        im_quaylai = findViewById(R.id.im_quaylai);
        btn_themgiohang = findViewById(R.id.btn_themgiohang);
        tv_soluongmua = findViewById(R.id.tv_soluongmua);
        img_tru = findViewById(R.id.img_tru);
        img_cong = findViewById(R.id.img_cong);
        tv_thanhtien = findViewById(R.id.tv_thanhtien);
        tv_linkanh = findViewById(R.id.tv_linkanh);
        im_vaogiohang = findViewById(R.id.im_vaogiohang);
        btn_muasp = findViewById(R.id.btn_muasp);
        tv_binhluansanpham = findViewById(R.id.tv_binhluansanpham);
        tv_idtest = findViewById(R.id.tv_idtest);
        tvttt = findViewById(R.id.tvttt);
        tvtt = findViewById(R.id.tvtt);

        rdos = findViewById(R.id.rdos);
        rdol = findViewById(R.id.rdol);
        rdoxl = findViewById(R.id.rdoxl);
    }
    private void muaSanPham(){
        progressDialog.show();

        SPMua spMua = new SPMua();

        String title = tv_title.getText().toString().trim();
        String image = tv_linkanh.getText().toString().trim();
        double pricegh = Double.parseDouble(tv_price.getText().toString().trim());
        int quantity = Integer.parseInt(tv_soluongmua.getText().toString().trim());
        double thanhtien = Double.parseDouble(tv_thanhtien.getText().toString());

        spMua.setTitle(tv_title.getText().toString().trim());
        spMua.setImage(tv_linkanh.getText().toString().trim());
        spMua.setPricegh(Double.parseDouble(tv_price.getText().toString().trim()));
        spMua.setThanhtien(Double.parseDouble(tv_thanhtien.getText().toString().trim()));
        spMua.setQuantity(Integer.parseInt(tv_soluongmua.getText().toString().trim()));

        if (rdos.isChecked()){
            spMua.setSize("S");
        }if (rdol.isChecked()) {
            spMua.setSize("L");

        }if (rdoxl.isChecked()) {
            spMua.setSize("XL");

        }
        Api_SPmua.apiSPmua.PostSPMUA(spMua).enqueue(new Callback<ReceSpMua>() {
            @Override
            public void onResponse(Call<ReceSpMua> call, retrofit2.Response<ReceSpMua> response) {
                Toast.makeText(getBaseContext(), "Đã mua sản phẩm", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ReceSpMua> call, Throwable t) {
                Toast.makeText(Infomation_Pro.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void addGiohang(){
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        try {
            JSONObject object = new JSONObject();
            object.put("title", tv_title.getText().toString().trim());
            object.put("image", tv_linkanh.getText().toString().trim());
            object.put("quantity", tv_soluongmua.getText().toString().trim());
            object.put("pricegh", tv_price.getText().toString().trim());
            object.put("thanhtien", tv_thanhtien.getText().toString());
            object.put("id_product", tv_idpro.getText().toString());
            object.put("id_user", iduser.getText().toString());

            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, API.API_PostGiohang, object,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.dismiss();
                            Toast.makeText(getBaseContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getBaseContext(), "Fail", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
            requestQueue.add(objectRequest);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}