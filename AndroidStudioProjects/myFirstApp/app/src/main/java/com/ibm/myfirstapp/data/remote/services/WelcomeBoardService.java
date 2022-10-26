package com.ibm.myfirstapp.data.remote.services;

import com.ibm.myfirstapp.data.remote.Response;
import com.ibm.myfirstapp.data.remote.requests.Request;
import com.ibm.myfirstapp.data.remote.requests.LoginUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WelcomeBoardService {

        @POST("welcome/")
        Call<Response> saveUser(@Body Request request);

        @POST("welcome/")
        Call<Response> loginUser(@Body LoginUser login);
}
