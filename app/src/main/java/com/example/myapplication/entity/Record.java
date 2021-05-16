package com.example.myapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Record {
    @PrimaryKey(autoGenerate = true)
    public int rid;

    @ColumnInfo(name = "pain_intensity_level")
    @NonNull
    public int painIntensityLevel;

    @ColumnInfo(name = "pain_location")
    @NonNull
    public String painLocation;

    @ColumnInfo(name = "mood")
    @NonNull
    public String mood;

    @ColumnInfo(name = "steps")
    @NonNull
    public int steps;

    @ColumnInfo(name = "date")
    @NonNull
    public String date;

    @ColumnInfo(name = "temperature")
    @NonNull
    public float temperature;

    @ColumnInfo(name = "humidity")
    @NonNull
    public int humidity;

    @ColumnInfo(name = "pressure")
    @NonNull
    public int pressure;

    @ColumnInfo(name = "email")
    @NonNull
    public String email;


    public Record(int painIntensityLevel, @NonNull String painLocation, @NonNull String mood, int steps, @NonNull String date, float temperature, int humidity, int pressure, @NonNull String email) {
        this.painIntensityLevel = painIntensityLevel;
        this.painLocation = painLocation;
        this.mood = mood;
        this.steps = steps;
        this.date = date;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.email = email;
    }
}
