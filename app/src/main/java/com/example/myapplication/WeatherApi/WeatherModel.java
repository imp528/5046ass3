package com.example.myapplication.WeatherApi;

import java.util.List;

public class WeatherModel {
    public Weathers data;
    public static class Condition {
        public int temp_C;
        public int humidity;
        public int pressure;
    }

    public static class Weathers {
        public List<Condition> current_condition;

    }
}

