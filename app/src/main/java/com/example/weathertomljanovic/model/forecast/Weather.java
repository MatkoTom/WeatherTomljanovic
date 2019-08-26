package com.example.weathertomljanovic.model.forecast;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("IconPhrase")
    public String phrase;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
