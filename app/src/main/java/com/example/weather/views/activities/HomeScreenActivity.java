package com.example.weather.views.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.weather.Base.BaseActivity;
import com.example.weather.R;
import com.example.weather.databinding.HomeScreenActivityBinding;
import com.example.weather.views.ViewModel.HomeScreenViewModel;

public class HomeScreenActivity extends BaseActivity {

    private HomeScreenActivityBinding binding;

    private HomeScreenViewModel homeScreenViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = HomeScreenActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeScreenViewModel = new ViewModelProvider(this).get(HomeScreenViewModel.class);
    }

}
