package com.example.myapplication;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface OpenWeatherMapInterface {
    @GET("weather")
    Call<JsonElement> getResult(@Query("lat") String latitude, @Query("lon") String longitude, @Query("APPID") String key, @Query("units") String units);
}
