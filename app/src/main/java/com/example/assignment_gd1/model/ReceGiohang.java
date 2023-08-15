package com.example.assignment_gd1.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReceGiohang {

    @SerializedName("data")
    private ArrayList<GioHang> data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<GioHang> getData() {
        return data;
    }

    public void setData(ArrayList<GioHang> data) {
        this.data = data;
    }
}
