package com.example.weather.apiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            return new Retrofit.Builder()
                    .baseUrl(Endpoints.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }
        return retrofit;
    }
}
