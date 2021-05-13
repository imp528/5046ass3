package com.example.myapplication.WeatherApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient {
    public static WeatherInterface getWeatherService(){
        return new Retrofit.Builder()
                .baseUrl("https://api.worldweatheronline.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WeatherInterface.class);
    }
}
