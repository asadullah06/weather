package com.example.weather.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.weather.Base.BaseActivity;
import com.example.weather.Models.HourlyWeatherDto;
import com.example.weather.Models.ListDto;
import com.example.weather.databinding.HomeScreenActivityBinding;
import com.example.weather.views.ViewModel.HomeScreenViewModel;

public class HomeScreenActivity extends BaseActivity {

    private HomeScreenActivityBinding binding;

    private HomeScreenViewModel homeScreenViewModel;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String str = data.getStringExtra("lat_long");

                        fetchWeatherDetails(str);
                        homeScreenViewModel.updateSelectedCityLatLong(str);
                    }
                });


        binding = HomeScreenActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeScreenViewModel = new ViewModelProvider(this).get(HomeScreenViewModel.class);

        addActions();

        homeScreenViewModel.getSelectedCityLatLong();
    }

    private void addActions() {
        binding.ivList.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreenActivity.this, AddNewCityActivity.class);
            activityResultLauncher.launch(intent);
        });

        homeScreenViewModel.getHourlyWeatherResult().observe(this, this::updateInformation);

        homeScreenViewModel.getSelectedCityLatLongResult().observe(this, this::fetchWeatherDetails);
    }


    private void updateInformation(HourlyWeatherDto hourlyWeatherDto) {

        if (hourlyWeatherDto != null && hourlyWeatherDto.getResponseCode() == 200) {
            ListDto item = hourlyWeatherDto.getListDto()[0];

            StringBuilder temperature = new StringBuilder();

            temperature.append(item.getTemperatureStats().getTemperature()).append("°");
            binding.tvTemperature.setText(temperature);

            StringBuilder minMaxTemp = new StringBuilder();

            minMaxTemp.append("H:").append(item.getTemperatureStats().getMaxTemperature()).append("°").append("  ");
            minMaxTemp.append("L:").append(item.getTemperatureStats().getMinTemperature()).append("°");

            binding.tvWeatherDetailsTwo.setText(minMaxTemp);
            binding.tvWeatherDetailsOne.setText(item.getWeatherDetails()[0].getDescription());

            binding.tvCityName.setText(hourlyWeatherDto.getCityInfo().getCityName());
        } else if (hourlyWeatherDto != null && !hourlyWeatherDto.getErrorMessage().isEmpty()) {
            Toast.makeText(this, hourlyWeatherDto.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchWeatherDetails(String str) {
        String[] latLong = str.split(",");

        float latitude = Float.parseFloat(latLong[0]);
        float longitude = Float.parseFloat(latLong[1]);

        homeScreenViewModel.getHourWeatherInfo(longitude, latitude);
    }
}
