package com.example.assignment_gd1.model;

import com.google.gson.annotations.SerializedName;

public class SPMua {

    @SerializedName("_id")
    String id;
    String image;
    String title;
    double pricegh;
    int quantity;
    double thanhtien;
    String date;
    String trangthai;
    String size;


    public SPMua(String id, String image, String title, double pricegh, int quantity, double thanhtien, String date, String trangthai) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.pricegh = pricegh;
        this.quantity = quantity;
        this.thanhtien = thanhtien;
        this.trangthai = trangthai;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public SPMua() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPricegh() {
        return pricegh;
    }

    public void setPricegh(double pricegh) {
        this.pricegh = pricegh;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
