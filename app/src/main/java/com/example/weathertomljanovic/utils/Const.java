package com.example.weathertomljanovic.utils;

public final class Const {

    private Const() {
    }


    public static class Api {
        public static final String BASE_WEATHER_URL = "http://dataservice.accuweather.com/locations/v1/cities/";
        public static final String BASE_YOUTUBE_API = "https://www.googleapis.com/youtube/v3/";
        public static final String URL_CITY = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/{city_key}";
        public static final String WEATHER_API_KEY = "FQaeUMQERWIRPCd99uLIzojr6vsQX1wy";
        public static final String YOUTUBE_API_KEY = "AIzaSyDhkBbKma3nYpeIok7imtXCEXxiaboFK18";
    }

    public static class NetworkQuery {
        public static final String YOUTUBE_PART = "snippet";
        public static final String SEARCH_URL = "search";
        public static final String QUERY = "q";
        public static final String QUERY_KEY_WEATHER = "apikey";
        public static final String QUERY_METRIC = "metric";
        public static final String QUERY_KEY_YOUTUBE = "key";
        public static final String QUERY_PART = "part";
        public static final String QUERY_MAX_RESULTS = "maxResults";
        public static final String PATH_CITY_KEY = "city_key";
    }
}
