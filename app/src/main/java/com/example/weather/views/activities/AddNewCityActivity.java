package com.example.weather.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weather.Base.BaseActivity;
import com.example.weather.Models.CurrentWeatherDto;
import com.example.weather.databinding.AddNewCityActivityBinding;
import com.example.weather.views.ViewModel.AddNewCityViewModel;
import com.example.weather.views.adapters.SearchedCitiesListingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * In this class search city name to fetch its current weather details and
 * list already searched cities along with its weather details.
 */
public class AddNewCityActivity extends BaseActivity {
    String TAG = AddNewCityActivity.class.getSimpleName();
    private AddNewCityActivityBinding binding;
    private AddNewCityViewModel addNewCityViewModel;


    private List<CurrentWeatherDto> searchedCitiesList = new ArrayList<>();
    private SearchedCitiesListingAdapter searchedCitiesListingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddNewCityActivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        searchedCitiesListingAdapter = new SearchedCitiesListingAdapter(searchedCitiesList, item -> {
            Intent intent = new Intent();
            intent.putExtra("lat_long", item.getCityCoordinates().getLatitude() + "," + item.getCityCoordinates().getLongitude());
            setResult(Activity.RESULT_OK, intent);
            this.finish();
        });
        setupAdapter();

        addNewCityViewModel = new ViewModelProvider(this).get(AddNewCityViewModel.class);
        addActions();

        addNewCityViewModel.getCurrentWeatherResult().observe(this, currentWeatherDto -> {
            Log.i(TAG, "API response observed");

            if (currentWeatherDto != null && currentWeatherDto.getResponseCode() == 200) {
                // update the searched history list
                searchedCitiesList.add(0, currentWeatherDto);
//                searchedCitiesListingAdapter.notifyItemChanged(searchedCitiesList.size() - 1);
                searchedCitiesListingAdapter.notifyDataSetChanged();
                // update city name in cache
                addNewCityViewModel.updateSearchedCity(currentWeatherDto.getCityName());

            } else if (currentWeatherDto != null && currentWeatherDto.getErrorMessage() != null) {
                Toast.makeText(AddNewCityActivity.this, currentWeatherDto.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
        });


        addNewCityViewModel.getSearchedCitiesResult().observe(this, this::fetchSearchedHistory);
    }

    /**
     * To all action that can be performed on views
     */
    private void addActions() {
        binding.tvSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String searchText = binding.tvSearch.getText().toString();
                if (!searchText.isEmpty())
                    addNewCityViewModel.searchCityAndWeatherDetails(searchText);
                return true;
            }
            return false;
        });
    }

    private void fetchSearchedHistory(String[] strings) {
        if (strings.length > 0) {
            for (String cityName : strings) {
                addNewCityViewModel.searchCityAndWeatherDetails(cityName);
            }
        }
    }

    private void setupAdapter() {
        binding.rvSearchedCities.setLayoutManager(new LinearLayoutManager(this));

        binding.rvSearchedCities.setAdapter(searchedCitiesListingAdapter);
    }
}
