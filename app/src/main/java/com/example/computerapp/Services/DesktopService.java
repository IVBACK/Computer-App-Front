package com.example.computerapp.Services;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.computerapp.Models.Desktop;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface DesktopService {

    @GET("desktops/")
    Call<List<Desktop>> getDesktops(@Header("Authorization")String token);
}



