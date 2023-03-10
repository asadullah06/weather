package com.example.weather.Repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.weather.Models.HourlyWeatherDto;
import com.example.weather.Utils.Constants;
import com.example.weather.Utils.WeatherApplication;
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

    public void getSelectedCityLatLong(ISelectedCityLatLongCallBack callBack) {
        SharedPreferences sharedPreferences = getSharedPref();

        String cityLatLong = sharedPreferences.getString(Constants.SELECTED_CITY.getValue(), "");
        callBack.onResponse(cityLatLong);
    }

    public void updateSelectedCityLatLong(String latLong) {
        SharedPreferences sharedPreferences = getSharedPref();
        sharedPreferences.edit().putString(Constants.SELECTED_CITY.getValue(), latLong).apply();
    }


    public interface IHourlyWeatherRespCallBack {
        void onResponse(HourlyWeatherDto hourlyWeatherDto);

        void onError(Throwable t);
    }

    public interface ISelectedCityLatLongCallBack {

        void onResponse(String cityLatLong);
    }

    private SharedPreferences getSharedPref() {
        return WeatherApplication.getAppContext().getSharedPreferences(Constants.SHARED_PREF_NAME.getValue(), Context.MODE_PRIVATE);
    }
}
