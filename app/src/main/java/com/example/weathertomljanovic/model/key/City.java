package com.example.weathertomljanovic.model.key;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("Key")
    private String key;

    @SerializedName("LocalizedName")
    private String localizedName;

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
