package com.example.assignment_gd1.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("_id")
    private String _id;
    private String image;
    private String title;
    private double price;
    int quantity;
    private String size;
    private String xuatsu;
    private String phongcach;
    private String infomation;

    public Product() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public String getXuatsu() {
        return xuatsu;
    }

    public void setXuatsu(String xuatsu) {
        this.xuatsu = xuatsu;
    }

    public String getPhongcach() {
        return phongcach;
    }

    public void setPhongcach(String phongcach) {
        this.phongcach = phongcach;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Product(String image, String title, double price, int quantity, String size, String xuatsu, String phongcach, String infomation) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.xuatsu = xuatsu;
        this.phongcach = phongcach;
        this.infomation = infomation;
    }

    public Product(String _id, String image, String title, double price, int quantity, String size, String xuatsu, String phongcach, String infomation) {
        this._id = _id;
        this.image = image;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.xuatsu = xuatsu;
        this.phongcach = phongcach;
        this.infomation = infomation;
    }
}
