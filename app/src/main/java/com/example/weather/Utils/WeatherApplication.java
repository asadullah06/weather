package com.example.weather.Utils;

import android.app.Application;
import android.content.Context;

public class WeatherApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        WeatherApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return WeatherApplication.context;
    }
}
