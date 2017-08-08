package com.example.owner.cafm.Models;


import com.example.owner.cafm.Objects.Request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse {

    @SerializedName("request")
    @Expose
    private Request request;

    public Request getRequests() {
        return request;
    }

    public void setRequests(Request requests) {
        this.request = requests;
    }
}
