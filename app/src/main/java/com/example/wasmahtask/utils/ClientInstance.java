package com.example.wasmahtask.utils;

import com.example.wasmahtask.urls.Url;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientInstance {
public static Retrofit retrofit=null;
static RetrofitInstance retrofitInstance=null;

public  static RetrofitInstance getRetrofit(){
    if (retrofitInstance==null){
        Gson gson=new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120,TimeUnit.SECONDS)
                .writeTimeout(120,TimeUnit.SECONDS)
                .build();

        retrofit=new Retrofit.Builder().baseUrl(Url.base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofitInstance=retrofit.create(RetrofitInstance.class);

    }
    return retrofitInstance;
}
}
