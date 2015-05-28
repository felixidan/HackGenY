package com.felixidan.example.networkcalldemo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idan on 29/05/2015.
 */
public class WeatherResponse {
    @SerializedName("main")
    public MainPayload main;
}
