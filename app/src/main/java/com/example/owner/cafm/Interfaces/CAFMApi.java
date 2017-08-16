package com.example.owner.cafm.Interfaces;


import com.example.owner.cafm.Models.OrderRequest;
import com.example.owner.cafm.Models.OrderResponse;
import com.example.owner.cafm.Models.RequestResponse;
import com.example.owner.cafm.Models.TokenRequest;
import com.example.owner.cafm.Models.TokenResponse;
import com.example.owner.cafm.Objects.Address;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit2.Callback;
import retrofit2.Response;

public interface CAFMApi {

    @POST("login")
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);

    @POST("requests")
    Call<OrderResponse> getOrderAccess(@Header("Authorization") String token , @Field("type") int type,
                                       @Field("emergency") int emergency,
                                       @Field("description") String description,
                                       @Field("photo") String photo,
                                       @Field("address")Address address,
                                       Callback<Response> callback);

    @POST("requests")
    Call<OrderResponse> getOrderAccess(@Header("Authorization") String token , @Body OrderRequest orderRequest);

    @GET("requests")
    Call<RequestResponse> getRequests(@Header("Authorization")String token);

}
