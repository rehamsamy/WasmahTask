package com.example.wasmahtask.utils;

import com.example.wasmahtask.models.JeffKellyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInstance {


    @GET("/users/JeffreyWay/repos")
    Call<List<JeffKellyModel>> getJeffKellyList(@Query("page")int page,
                                                @Query("amp;per_page") int limit);

}
