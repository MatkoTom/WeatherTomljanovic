package com.example.weathertomljanovic.network;

import com.example.weathertomljanovic.model.forecast.ForecastBigResponse;
import com.example.weathertomljanovic.model.key.City;
import com.example.weathertomljanovic.model.youTube.YouTubeResponse;
import com.example.weathertomljanovic.utils.Const;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET(Const.NetworkQuery.SEARCH_URL)
    Single<List<City>> getCityKey(@Query(Const.NetworkQuery.QUERY) String cityName,
                                  @Query(Const.NetworkQuery.QUERY_KEY_WEATHER) String api);

    @GET(Const.Api.URL_CITY)
    Single<ForecastBigResponse> getWeatherForLocation(@Path(Const.NetworkQuery.PATH_CITY_KEY) String key,
                                                      @Query(Const.NetworkQuery.QUERY_KEY_WEATHER) String apiKey,
                                                      @Query(Const.NetworkQuery.QUERY_METRIC) boolean metric);

    @GET(Const.NetworkQuery.SEARCH_URL)
    Single<YouTubeResponse> getVideoId(@Query(Const.NetworkQuery.QUERY_KEY_YOUTUBE) String key,
                                       @Query(Const.NetworkQuery.QUERY_PART) String part,
                                       @Query(Const.NetworkQuery.QUERY_MAX_RESULTS) String results,
                                       @Query(Const.NetworkQuery.QUERY) String query);
}
