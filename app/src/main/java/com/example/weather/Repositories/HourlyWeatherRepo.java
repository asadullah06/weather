package com.example.weather.Repositories;

import com.example.weather.Models.HourlyWeatherDto;
import com.example.weather.Utils.Constants;
import com.example.weather.apiInterface.ApiServices;
import com.example.weather.apiInterface.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HourlyWeatherRepo {

    public void getHourlyWeather(float lon, float lat, IHourlyWeatherRespCallBack callBack) {
        ApiServices apiServices = RetrofitClientInstance.getRetrofit().create(ApiServices.class);

        Call<HourlyWeatherDto> cityWeatherHourly = apiServices.getHourlyWeather(lat, lon, Constants.CELSIUS.getValue(), Constants.API_KEY.getValue());
        cityWeatherHourly.enqueue(new Callback<HourlyWeatherDto>() {
            @Override
            public void onResponse(Call<HourlyWeatherDto> call, Response<HourlyWeatherDto> response) {
                if (response.isSuccessful()) {
                    callBack.onResponse(response.body());
                } else {
                    callBack.onError(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<HourlyWeatherDto> call, Throwable t) {
                callBack.onError(t);
            }
        });

    }


    public interface IHourlyWeatherRespCallBack {
        void onResponse(HourlyWeatherDto hourlyWeatherDto);

        void onError(Throwable t);
    }
}
