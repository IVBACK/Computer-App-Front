package com.example.computerapp.Services;

import com.example.computerapp.Models.Desktop;
import com.example.computerapp.Models.Notebook;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NotebookService {

    @GET("notebooks/")
    Call<List<Notebook>> getNotebooks(@Header("Authorization")String token);

    @POST("notebooks/search/")
    Call<List<Notebook>> getFilteredNotebooks(@Header("Authorization")String token, @Body String search);
}
