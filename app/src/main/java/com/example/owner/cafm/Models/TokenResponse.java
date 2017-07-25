package com.example.owner.cafm.Models;


import com.example.owner.cafm.Objects.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse {


    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("result")
    @Expose
    private int result;
    @SerializedName("user")
    @Expose
    private User user;

    public String getToken() {
        return token;
    }

    public int getResult() {
        return result;
    }

    public User getUser() {
        return user;
    }
}
