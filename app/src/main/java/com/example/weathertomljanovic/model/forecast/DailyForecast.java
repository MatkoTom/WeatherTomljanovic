package com.example.weathertomljanovic.model.forecast;

import com.google.gson.annotations.SerializedName;

public class DailyForecast {

    @SerializedName("Date")
    public String date;

    @SerializedName("Temperature")
    public Temperature temperature;

    @SerializedName("Day")
    public Weather weather;
}
