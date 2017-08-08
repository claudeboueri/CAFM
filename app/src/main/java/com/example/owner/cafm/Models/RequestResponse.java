package com.example.owner.cafm.Models;

import com.example.owner.cafm.Objects.Request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RequestResponse {



    @SerializedName("requests")
    @Expose
    private Request[] requests;

    public Request[] getRequests() {
        return requests;
    }

    public void setRequests(Request[] requests) {
        this.requests = requests;
    }
}
