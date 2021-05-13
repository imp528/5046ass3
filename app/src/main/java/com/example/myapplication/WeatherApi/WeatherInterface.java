package com.example.myapplication.WeatherApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {
    @GET("premium/v1/weather.ashx")
    Call<WeatherModel> getWeather(@Query("q") String q, @Query("date") String date,
                                            @Query("format") String format, @Query("key") String key);
}
