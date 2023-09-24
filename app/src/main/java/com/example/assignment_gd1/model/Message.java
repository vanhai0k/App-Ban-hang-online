package com.example.assignment_gd1.model;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("_id")
    String id;
    String content;
    String username;
    String image;

    public Message(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public Message() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
