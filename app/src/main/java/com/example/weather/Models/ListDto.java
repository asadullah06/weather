package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

public class ListDto {

    @SerializedName("weather")
    private WeatherDetails[] weatherDetails;

    @SerializedName("main")
    private TemperatureStats temperatureStats;

    @SerializedName("rain")
    private String rainInfo;

    @SerializedName("dt_txt")
    private String dateTime;

    public WeatherDetails[] getWeatherDetails() {
        return weatherDetails;
    }

    public TemperatureStats getTemperatureStats() {
        return temperatureStats;
    }

    public String getRainInfo() {
        return rainInfo;
    }

    public String getDateTime() {
        return dateTime;
    }
}
