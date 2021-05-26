package com.example.mapremireapplicationavecalexandrequivatresuperbe.api;

import com.example.mapremireapplicationavecalexandrequivatresuperbe.model.SessionToken;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface AuthAPI {

    @GET("ping")
    Call<String> ping();

    @POST("v1/authentication/login")
    @Headers({"Content-type: application/json","accept: */*"})
    Call<SessionToken> loginUser(@Body JsonObject authInput);

}
