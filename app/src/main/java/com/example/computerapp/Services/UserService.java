package com.example.computerapp.Services;

import com.example.computerapp.Models.User;
import com.example.computerapp.Requests.UserLoginRequest;
import com.example.computerapp.Requests.UserRegisterRequest;
import com.example.computerapp.Responses.UserLoginResponse;
import com.example.computerapp.Responses.UserRegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("users/login/")
    Call<UserLoginResponse> loginUser(@Body UserLoginRequest userLoginRequest);

    @POST("users/register/")
    Call<UserRegisterResponse> registerUser(@Body UserRegisterRequest userRegisterRequest);

}
