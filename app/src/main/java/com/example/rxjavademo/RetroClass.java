package com.example.rxjavademo;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {

    private static final String BASE_URL="https://simplifiedcoding.net/demos/";

    private static Retrofit getRetrofitInstance()
    {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public static Api getApi()
    {
        return getRetrofitInstance().create(Api.class);
    }
}
