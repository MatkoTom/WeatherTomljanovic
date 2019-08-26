package com.example.weathertomljanovic.model.forecast;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ForecastBigResponse {

    @SerializedName("DailyForecasts")
    public ArrayList<DailyForecast> forecast;
}
