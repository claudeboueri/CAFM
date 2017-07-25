package com.example.owner.cafm.Interfaces;


import com.example.owner.cafm.Models.TokenRequest;
import com.example.owner.cafm.Models.TokenResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

public interface CAFMApi {

    @POST("login")
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);

}
