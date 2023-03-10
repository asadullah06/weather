package com.example.weather.views.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.Models.HourlyWeatherDto;
import com.example.weather.Repositories.HourlyWeatherRepo;

public class HomeScreenViewModel extends ViewModel {

    MutableLiveData<HourlyWeatherDto> mHourlyWeatherResult = new MutableLiveData<>();
    MutableLiveData<String> mSelectedCityResult = new MutableLiveData<>();

    HourlyWeatherRepo hourlyWeatherRepo;

    public HomeScreenViewModel() {
        mHourlyWeatherResult.postValue(null);
        hourlyWeatherRepo = new HourlyWeatherRepo();
    }

    public void getHourWeatherInfo(float lon, float lat) {

        hourlyWeatherRepo.getHourlyWeather(lon, lat, new HourlyWeatherRepo.IHourlyWeatherRespCallBack() {
            @Override
            public void onResponse(HourlyWeatherDto currentWeatherDto) {
                mHourlyWeatherResult.postValue(currentWeatherDto);
            }

            @Override
            public void onError(Throwable t) {
                HourlyWeatherDto errorDto = new HourlyWeatherDto();

                errorDto.setErrorMessage(t.getLocalizedMessage());

                mHourlyWeatherResult.postValue(errorDto);
            }
        });
    }

    public void getSelectedCityLatLong() {
        hourlyWeatherRepo.getSelectedCityLatLong(cityLatLong -> {
            if (!cityLatLong.isEmpty())
                mSelectedCityResult.postValue(cityLatLong);
        });
    }

    public void updateSelectedCityLatLong(String latLong) {
        hourlyWeatherRepo.updateSelectedCityLatLong(latLong);
    }

    public LiveData<HourlyWeatherDto> getHourlyWeatherResult() {
        return mHourlyWeatherResult;
    }

    public LiveData<String> getSelectedCityLatLongResult() {
        return mSelectedCityResult;
    }
}
