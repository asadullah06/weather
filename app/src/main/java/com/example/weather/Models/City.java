package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("name")
    private String cityName;

    @SerializedName("coord")
    private CityCoordinates cityCoordinates;

    public String getCityName() {
        return cityName;
    }

    public CityCoordinates getCityCoordinates() {
        return cityCoordinates;
    }
}
