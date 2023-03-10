package com.example.weather.apiInterface;

import com.example.weather.Models.CurrentWeatherDto;
import com.example.weather.Models.HourlyWeatherDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET(Endpoints.CURRENT_WEATHER_BY_CITY_NAME)
    Call<CurrentWeatherDto> getCurrentWeather(@Query("q") String cityName, @Query("units") String tempUnit, @Query("appid") String apiKey);

    @GET(Endpoints.THREE_HOUR_WEATHER_BY_LAT_LONG)
    Call<HourlyWeatherDto> getHourlyWeather(@Query("lat") float latitude, @Query("lon") float longitude, @Query("units") String tempUnit, @Query("appid") String apiKey);

}
