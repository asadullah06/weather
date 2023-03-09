package com.example.weather.views.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.weather.Base.BaseActivity;
import com.example.weather.R;
import com.example.weather.databinding.HomeScreenActivityBinding;

public class HomeScreenActivity extends BaseActivity {

    private HomeScreenActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = HomeScreenActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
