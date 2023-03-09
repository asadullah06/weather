package com.example.weather.views.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.Models.CurrentWeatherDto;
import com.example.weather.Repositories.SearchCityWeatherRepo;

public class AddNewCityViewModel extends ViewModel {

    MutableLiveData<CurrentWeatherDto> mCurrentWeatherResult = new MutableLiveData<>();

    SearchCityWeatherRepo searchCityWeatherRepo;


    public AddNewCityViewModel() {
        mCurrentWeatherResult.postValue(null);

        searchCityWeatherRepo = new SearchCityWeatherRepo();
    }

    public void searchCityAndWeatherDetails(String cityName) {
        searchCityWeatherRepo.getCurrentWeather(cityName, new SearchCityWeatherRepo.ICurrentWeatherRespCallBack() {
            @Override
            public void onResponse(CurrentWeatherDto currentWeatherDto) {
                mCurrentWeatherResult.postValue(currentWeatherDto);
            }

            @Override
            public void onError(Throwable t) {
                CurrentWeatherDto errorDto = new CurrentWeatherDto();

                errorDto.setErrorMessage(t.getLocalizedMessage());

                mCurrentWeatherResult.postValue(errorDto);
            }
        });
    }

    public LiveData<CurrentWeatherDto> getCurrentWeatherResult(){
        return mCurrentWeatherResult;
    }

}
