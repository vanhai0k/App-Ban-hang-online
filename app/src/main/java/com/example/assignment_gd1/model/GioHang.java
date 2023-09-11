package com.example.assignment_gd1.model;

import com.google.gson.annotations.SerializedName;

public class GioHang {
    @SerializedName("_id")
    String id;
    String image;
    String title;
    double pricegh;
    int quantity;
    double thanhtien;
    String date;
    String id_user,id_product;

    public GioHang(String id, String image, String title, double pricegh, int quantity, double thanhtien) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.pricegh = pricegh;
        this.quantity = quantity;
        this.thanhtien = thanhtien;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public GioHang() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
