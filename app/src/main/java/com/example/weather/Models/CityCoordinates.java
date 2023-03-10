package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

public class CityCoordinates {
    @SerializedName("lon")
    private float longitude;

    @SerializedName("lat")
    private float latitude;

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
