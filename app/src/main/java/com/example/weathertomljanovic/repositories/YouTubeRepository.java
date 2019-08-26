package com.example.weathertomljanovic.repositories;

import com.example.weathertomljanovic.model.youTube.YouTubeResponse;
import com.example.weathertomljanovic.network.youTube.YouTubeRetrofitAdapter;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class YouTubeRepository {

    public YouTubeRepository() {}

    public Single<YouTubeResponse> getVideoId(String key, String part, String maxResults, String query) {
        return YouTubeRetrofitAdapter.getRetrofitClient()
                .getVideoId(key, part, maxResults, query)
                .subscribeOn(Schedulers.io());
    }
}
