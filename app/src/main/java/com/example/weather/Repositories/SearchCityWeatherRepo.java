package com.example.weather.Repositories;

import com.example.weather.Models.CurrentWeatherDto;
import com.example.weather.Utils.Constants;
import com.example.weather.apiInterface.ApiServices;
import com.example.weather.apiInterface.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCityWeatherRepo {

    public void getCurrentWeather(String cityName, ICurrentWeatherRespCallBack callBack) {
        ApiServices apiServices = RetrofitClientInstance.getRetrofit().create(ApiServices.class);

        Call<CurrentWeatherDto> cityWeather = apiServices.getCurrentWeather(cityName, Constants.API_KEY.getValue());

        cityWeather.enqueue(new Callback<CurrentWeatherDto>() {
            @Override
            public void onResponse(Call<CurrentWeatherDto> call, Response<CurrentWeatherDto> response) {
                if (response.isSuccessful()) {
                    callBack.onResponse(response.body());
                } else {
                    callBack.onError(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherDto> call, Throwable t) {
                callBack.onError(t);
            }
        });
    }


    public interface ICurrentWeatherRespCallBack {
        void onResponse(CurrentWeatherDto currentWeatherDto);

        void onError(Throwable t);
    }
}
