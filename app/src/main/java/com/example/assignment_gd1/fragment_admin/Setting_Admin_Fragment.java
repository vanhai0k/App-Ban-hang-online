package com.example.assignment_gd1.fragment_admin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.assignment_gd1.MainActivity;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.model.Product;
import com.example.assignment_gd1.model.Thongke;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class Setting_Admin_Fragment extends Fragment {

    ImageView im_image,imDate,imDateand;
    private TextView tv_username_admin,tv_email_admin,tv_phanquyen,tvthongke,
            tvthongkedate,tv_tongtienDate;
    Button btndangxuat;
    EditText startdate,enddate;
    String datestart,dateend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting__admin_, container, false);

        tv_email_admin = view.findViewById(R.id.tv_email_admin);
        tv_username_admin = view.findViewById(R.id.tv_username_admin);
        tv_phanquyen = view.findViewById(R.id.tv_phanquyen);
        startdate = view.findViewById(R.id.startdate);
        tvthongke = view.findViewById(R.id.tvthongke);
        tvthongkedate = view.findViewById(R.id.tvthongkedate);
        tv_tongtienDate = view.findViewById(R.id.tv_tongtienDate);
        enddate = view.findViewById(R.id.enddate);
        im_image = view.findViewById(R.id.im_image);
        imDate = view.findViewById(R.id.imDate);
        imDateand = view.findViewById(R.id.imDateand);
        btndangxuat = view.findViewById(R.id.btndangxuat);

        imDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        imDateand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialogEnd();
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

        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);

                startActivity(intent);
            }
        });

        TongTien();
        tv_tongtienDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TongTienDate();            }
        });


        return view;
    }

    private void showDatePickerDialogEnd() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Xử lý khi người dùng chọn ngày
                        month++; // Tháng trong DatePickerDialog bắt đầu từ 0
                        dateend = dayOfMonth +"/"+ month +"/"+ year;
                        enddate.setText(dateend);
                    }
                },
                year, month, day);

        datePickerDialog.show();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Xử lý khi người dùng chọn ngày
                        month++; // Tháng trong DatePickerDialog bắt đầu từ 0
                        datestart = dayOfMonth +"/"+ month +"/"+ year;
                        startdate.setText(datestart);
                    }
                },
                year, month, day);

        datePickerDialog.show();
    }

    private void TongTien(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.API_Tongtien,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int totalAmount = jsonObject.getInt("totalAmount");

                            tvthongke.setText(String.valueOf(totalAmount));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi
                    }
                });

        requestQueue.add(stringRequest);
    }
    private void TongTienDate(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API.API_TongtienMonth +"?startDate=" +datestart +"&endDate=" + dateend,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int totalAmount = jsonObject.getInt("totalAmount");

                            tvthongkedate.setText(String.valueOf(totalAmount ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi
                    }
                });

        requestQueue.add(stringRequest);
    }
}