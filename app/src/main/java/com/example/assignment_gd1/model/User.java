package com.example.assignment_gd1.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("_id")
    private String id;
    private String passwd,username,phanquyen,email,image;

    public User() {
    }

    public User(String id, String passwd, String username, String phanquyen, String email, String image) {
        this.id = id;
        this.passwd = passwd;
        this.username = username;
        this.phanquyen = phanquyen;
        this.email = email;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhanquyen() {
        return phanquyen;
    }

    public void setPhanquyen(String phanquyen) {
        this.phanquyen = phanquyen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(String phanquyen) {
        this.phanquyen = phanquyen;
    }

    public User(String id, String phanquyen) {
        this.id = id;
        this.phanquyen = phanquyen;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", passwd='" + passwd + '\'' +
                ", username='" + username + '\'' +
                ", phanquyen='" + phanquyen + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
