package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

public class HourlyWeatherDto {

    @SerializedName("cod")
    private int responseCode;
    @SerializedName("list")
    private ListDto[] listDto;

    @SerializedName("city")
    private City cityInfo;

    private String errorMessage;


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public ListDto[] getListDto() {
        return listDto;
    }

    public City getCityInfo() {
        return cityInfo;
    }
}
