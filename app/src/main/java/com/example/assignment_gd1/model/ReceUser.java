package com.example.assignment_gd1.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReceUser {
    @SerializedName("data")
    private ArrayList<User> data;
    private String msg;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<User> getData() {
        return data;
    }

    public void setData(ArrayList<User> data) {
        this.data = data;
    }
}
