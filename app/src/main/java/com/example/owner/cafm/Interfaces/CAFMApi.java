package com.example.owner.cafm.Interfaces;


import com.example.owner.cafm.Models.OrderRequest;
import com.example.owner.cafm.Models.OrderResponse;
import com.example.owner.cafm.Models.RequestResponse;
import com.example.owner.cafm.Models.TokenRequest;
import com.example.owner.cafm.Models.TokenResponse;
import com.example.owner.cafm.Objects.Request;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit2.Response;
import retrofit2.http.Field;

public interface CAFMApi {

    @POST("login")
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);

    @POST("requests")
    Call<OrderResponse> getOrderAccess(@Header("Authorization") String token ,@Body OrderRequest orderRequest);

    @GET("user")
    Call<RequestResponse> getUser(@Header("Authorization") String authorization );





}
