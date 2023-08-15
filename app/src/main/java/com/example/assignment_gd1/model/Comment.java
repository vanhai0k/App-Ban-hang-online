package com.example.assignment_gd1.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("_id")
    String id;
    String username,comment,title;
    String id_user,id_product;

    public Comment(String username, String comment, String title, String id_user, String id_product) {
        this.username = username;
        this.comment = comment;
        this.title = title;
        this.id_user = id_user;
        this.id_product = id_product;
    }

    public Comment(String id, String username, String comment, String title, String id_user, String id_product) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.title = title;
        this.id_user = id_user;
        this.id_product = id_product;
    }

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Comment(String id, String username, String comment) {
        this.id = id;
        this.username = username;
        this.comment = comment;
    }

    public Comment(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
