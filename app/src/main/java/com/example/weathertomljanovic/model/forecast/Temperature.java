package com.example.weathertomljanovic.model.forecast;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("Minimum")
    public MinimumTemp minimumTemp;

    @SerializedName("Maximum")
    public MaximumTemp maximumTemp;
}
