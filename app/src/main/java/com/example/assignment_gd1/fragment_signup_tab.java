package com.example.assignment_gd1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.model.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class fragment_signup_tab extends Fragment {

    EditText ed_username,ed_email,ed_pass;
    Button signup_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_signup_tab, container, false);

        ed_username = view.findViewById(R.id.ed_username);
        ed_email = view.findViewById(R.id.ed_email);
        ed_pass = view.findViewById(R.id.ed_password);
        signup_button = view.findViewById(R.id.signup_button);


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_username.getText().toString().trim();
                String password = ed_pass.getText().toString().trim();
                String email = ed_email.getText().toString().trim();

                if (isValied(name,password,email)){
                    registerUser();
                }
            }
        });
        return view;
    }

    private void showMessage(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
    private boolean isValied(String name, String password, String email) {
        if (name.isEmpty()){
            showMessage("Please enter user name");
            return false;
        }
        if (email.isEmpty()){
            showMessage("Please enter email");
            return false;
        }
        if (password.isEmpty()){
            showMessage("Please enter password");
            return false;
        }
        if (password.length() < 0){
            showMessage("mat khau yeu");
            return false;
        }
        return true;
    }

    private void registerUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        try {
            JSONObject object = new JSONObject();
            object.put("username",ed_username.getText().toString().trim());
            object.put("email",ed_email.getText().toString().trim());
            object.put("passwd",ed_pass.getText().toString().trim());

            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, API.API_PostUser, object, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getContext(), "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                    ed_username.setText("");
                    ed_email.setText("");
                    ed_pass.setText("");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(objectRequest);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}