package com.ibm.myfirstapp.data.remote.services;

import com.ibm.myfirstapp.data.remote.UserResponse;
import com.ibm.myfirstapp.data.remote.requests.UserRequest;
import com.ibm.myfirstapp.data.remote.requests.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WelcomeBoardService {

        @POST("welcome/register/")
        Call<UserResponse> saveUser(@Body UserRequest request);

        @POST("welcome/login/")
        Call<UserResponse> loginUser(@Body UserLogin login);

}
