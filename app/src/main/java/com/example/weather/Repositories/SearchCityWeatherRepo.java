package com.example.weather.Repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.weather.Models.CurrentWeatherDto;
import com.example.weather.Utils.Constants;
import com.example.weather.Utils.WeatherApplication;
import com.example.weather.apiInterface.ApiServices;
import com.example.weather.apiInterface.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCityWeatherRepo {

    public void getCurrentWeather(String cityName, ICurrentWeatherRespCallBack callBack) {
        ApiServices apiServices = RetrofitClientInstance.getRetrofit().create(ApiServices.class);

        Call<CurrentWeatherDto> cityWeather = apiServices.getCurrentWeather(cityName, Constants.CELSIUS.getValue(), Constants.API_KEY.getValue());

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

    public void getSearchedCities(ISearchedCitiesCallback callback) {
        SharedPreferences sharedPreferences = WeatherApplication.getAppContext().getSharedPreferences(Constants.SHARED_PREF_NAME.getValue(), Context.MODE_PRIVATE);

        String searchedCities = sharedPreferences.getString(Constants.SEARCHED_HISTORY.getValue(), "");

        if (!searchedCities.isEmpty()) {
            String[] citiesList = searchedCities.split(",");
            callback.onResponse(citiesList);
        } else {
            callback.onResponse(new String[]{});
        }

    }

    /**
     * @param cityName that needs to update in searched history list
     */
    public void updateSearchedCitiesList(String cityName) {
        SharedPreferences sharedPreferences = WeatherApplication.getAppContext().getSharedPreferences(Constants.SHARED_PREF_NAME.getValue(), Context.MODE_PRIVATE);

        String searchedCities = sharedPreferences.getString(Constants.SEARCHED_HISTORY.getValue(), "");
        StringBuilder stringBuilder = new StringBuilder(searchedCities);
        if (!searchedCities.isEmpty()) {
            if (!isAlreadyCityExitsInHistory(searchedCities.split(","), cityName)) {
                stringBuilder.append(",").append(cityName);
            }
        } else {
            stringBuilder.append(cityName);
        }

        sharedPreferences.edit().putString(Constants.SEARCHED_HISTORY.getValue(), stringBuilder.toString()).apply();
    }


    public interface ICurrentWeatherRespCallBack {
        void onResponse(CurrentWeatherDto currentWeatherDto);

        void onError(Throwable t);
    }

    public interface ISearchedCitiesCallback {

        void onResponse(String[] citiesList);
    }

    private boolean isAlreadyCityExitsInHistory(String[] searchList, String cityName) {
        for (String name : searchList) {
            if (name.equals(cityName)) {
                return true;
            }
        }
        return false;
    }
}
