package com.example.rxjavademo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    /*Observable<List<Hero>> getHeros()*/;
    @GET("marvel")
    Observable<List<Hero>> getHeros();
}
