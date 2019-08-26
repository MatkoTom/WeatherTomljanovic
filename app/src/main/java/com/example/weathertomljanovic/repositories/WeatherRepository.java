package com.example.weathertomljanovic.repositories;

import com.example.weathertomljanovic.model.forecast.ForecastBigResponse;
import com.example.weathertomljanovic.model.key.City;
import com.example.weathertomljanovic.network.forecast.RetrofitAdapter;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository {

    public WeatherRepository() {
    }

    public Single<List<City>> getKey(String api, String city) {
        return RetrofitAdapter.getRetrofitClient()
                .getCityKey(api, city)
                .subscribeOn(Schedulers.io());
    }

    public Single<ForecastBigResponse> getWeather(String key, String api, boolean metric) {
        return RetrofitAdapter.getRetrofitClient()
                .getWeatherForLocation(key, api, metric)
                .subscribeOn(Schedulers.io());
    }
}
