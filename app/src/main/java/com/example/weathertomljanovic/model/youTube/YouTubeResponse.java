package com.example.weathertomljanovic.model.youTube;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class YouTubeResponse {

    @SerializedName("items")
    public ArrayList<Items> items;
}
