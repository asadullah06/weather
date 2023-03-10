package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

public class TemperatureStats {
    @SerializedName("temp")
    private float temperature;

    @SerializedName("feels_like")
    private float feelsLike;

    @SerializedName("temp_min")
    private float minTemperature;

    @SerializedName("temp_max")
    private float maxTemperature;

    public float getTemperature() {
        return temperature;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }
}
