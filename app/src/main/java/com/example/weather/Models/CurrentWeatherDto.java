package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

public class CurrentWeatherDto {

    @SerializedName("cod")
    private int responseCode;

    private String ErrorMessage;

    @SerializedName("coord")
    private CityCoordinates cityCoordinates;

    @SerializedName("weather")
    private WeatherDetails[] weatherDetails;

    @SerializedName("main")
    private TemperatureStats temperatureStats;

    @SerializedName("name")
    private String cityName;


    public CityCoordinates getCityCoordinates() {
        return cityCoordinates;
    }

    public WeatherDetails[] getWeatherDetails() {
        return weatherDetails;
    }

    public TemperatureStats getTemperatureStats() {
        return temperatureStats;
    }

    public String getCityName() {
        return cityName == null ? "" : cityName;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
}


class CityCoordinates {
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