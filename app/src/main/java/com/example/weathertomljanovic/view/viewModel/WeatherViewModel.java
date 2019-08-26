package com.example.weathertomljanovic.view.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.weathertomljanovic.model.forecast.ForecastBigResponse;
import com.example.weathertomljanovic.model.key.City;
import com.example.weathertomljanovic.repositories.WeatherRepository;
import com.example.weathertomljanovic.utils.Const;
import com.example.weathertomljanovic.utils.helpers.SingleLiveEvent;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel extends ViewModel {

    public static final String TAG = "CITYKEY";

    private SingleLiveEvent<ForecastBigResponse> response = new SingleLiveEvent<>();
    private SingleLiveEvent<List<City>> cityList = new SingleLiveEvent<>();

    private WeatherRepository weatherRepository;

    public WeatherViewModel() {
        weatherRepository = new WeatherRepository();
    }

    public SingleLiveEvent<ForecastBigResponse> getResponse() {
        return response;
    }

    public SingleLiveEvent<List<City>> getKey() {
        return cityList;
    }

    public void getCityKey(String api, String city) {
        weatherRepository.getKey(api, city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<City>>() {
                    @Override
                    public void onSuccess(List<City> cityResponse) {
                        getKey().setValue(cityResponse);
                        dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dispose();
                    }
                });
    }

    public void getWeatherData(String key, String api, boolean metric) {
        weatherRepository.getWeather(key, api, metric)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ForecastBigResponse>() {
                    @Override
                    public void onSuccess(ForecastBigResponse responseWeather) {
                        getResponse().setValue(responseWeather);
                        dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dispose();
                    }
                });
    }

    public void cityKey(String city) {
        getCityKey(city, Const.Api.WEATHER_API_KEY);
    }

    public void weatherData(String key) {
        getWeatherData(key, Const.Api.WEATHER_API_KEY, true);
    }
}
