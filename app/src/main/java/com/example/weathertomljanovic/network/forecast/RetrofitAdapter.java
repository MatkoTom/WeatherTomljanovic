package com.example.weathertomljanovic.network.forecast;

import com.example.weathertomljanovic.BuildConfig;
import com.example.weathertomljanovic.network.RetrofitAPI;
import com.example.weathertomljanovic.utils.Const;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitAdapter {

    private static Retrofit retrofitInstance = null;

    public static RetrofitAPI getRetrofitClient() {

        if (retrofitInstance == null) {

            OkHttpClient.Builder okHttpBuilder;
            okHttpBuilder = new OkHttpClient.Builder();

            okHttpBuilder.readTimeout(25, TimeUnit.SECONDS);
            okHttpBuilder.connectTimeout(25, TimeUnit.SECONDS);

            if (BuildConfig.BUILD_TYPE.equals("debug")
                    || BuildConfig.BUILD_TYPE.equals("dev")
                    || BuildConfig.BUILD_TYPE.equals("previewDebug")
                    || BuildConfig.BUILD_TYPE.equals("preview")
                    || BuildConfig.BUILD_TYPE.equals("releaseDebug")) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.level(HttpLoggingInterceptor.Level.BODY);
                okHttpBuilder.addInterceptor(interceptor);
            }

            OkHttpClient okHttpClient = okHttpBuilder.build();

            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .baseUrl(Const.Api.BASE_WEATHER_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

            retrofitInstance = retrofitBuilder.build();
        }

        return retrofitInstance.create(RetrofitAPI.class);
    }
}
