package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

public class HourlyWeatherDto {

    @SerializedName("weather")
    private WeatherDetails[] weatherDetails;

    @SerializedName("main")
    private TemperatureStats temperatureStats;

    @SerializedName("rain")
    private String rainInfo;

    @SerializedName("dt_txt")
    private String dateTime;

    private String ErrorMessage;

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

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }
}
