package com.example.computerapp.Services;

import com.example.computerapp.Models.Desktop;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface DesktopService {

    @GET("desktops/")
    Call<List<Desktop>> getDesktops();
}
