package com.example.assignment_gd1.model;

import com.google.gson.annotations.SerializedName;

public class Thongke {
    @SerializedName("_id")
    String id;
    String sumprice;

    public Thongke() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSumprice() {
        return sumprice;
    }

    public void setSumprice(String sumprice) {
        this.sumprice = sumprice;
    }
}

