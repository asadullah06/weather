package com.example.weather.views.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.weather.Base.BaseActivity;
import com.example.weather.R;

public class HomeScreenActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);
    }
}
