package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("country")
    private String countryShortName;

    @SerializedName("sunrise")
    private String sunrise;

    @SerializedName("sunset")
    private String sunset;

    public String getCountryShortName() {
        return countryShortName;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
