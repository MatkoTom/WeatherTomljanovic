package com.example.weathertomljanovic.model.youTube;

import com.google.gson.annotations.SerializedName;

public class Id {

    @SerializedName("videoId")
    public String videoId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
