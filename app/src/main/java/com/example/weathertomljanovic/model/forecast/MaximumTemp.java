package com.example.weathertomljanovic.model.forecast;

import com.google.gson.annotations.SerializedName;

public class MaximumTemp {

    @SerializedName("Value")
    private String value;

    @SerializedName("Unit")
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
