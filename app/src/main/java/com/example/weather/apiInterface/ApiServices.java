package com.example.weather.apiInterface;

import com.example.weather.Models.CurrentWeatherDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET(Endpoints.CURRENT_WEATHER_BY_CITY_NAME)
    Call<CurrentWeatherDto> getCurrentWeather(@Query("q") String cityName, @Query("appid") String apiKey);

}
