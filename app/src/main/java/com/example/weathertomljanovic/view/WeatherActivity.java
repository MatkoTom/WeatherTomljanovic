package com.example.weathertomljanovic.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathertomljanovic.R;
import com.example.weathertomljanovic.model.forecast.DailyForecast;
import com.example.weathertomljanovic.model.key.City;
import com.example.weathertomljanovic.model.youTube.Items;
import com.example.weathertomljanovic.utils.Const;
import com.example.weathertomljanovic.view.adapter.WeatherListAdapter;
import com.example.weathertomljanovic.view.viewModel.WeatherViewModel;
import com.example.weathertomljanovic.view.viewModel.YouTubeViewModel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends AppCompatActivity {

    public static final String TAG = "CITYKEY";

    @BindView(R.id.et_location)
    EditText etLocation;

    @BindView(R.id.rv_forecast)
    RecyclerView rvForecast;

    @BindView(R.id.tv_today)
    TextView tvToday;

    private WeatherViewModel viewModel;
    private YouTubeViewModel youTubeViewModel;
    private YouTubePlayer mPlayer;
    private WeatherListAdapter adapter;
    private String weatherToday = "";
    private String cityName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        YouTubePlayerFragment youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);

        viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        youTubeViewModel = ViewModelProviders.of(this).get(YouTubeViewModel.class);

        viewModel.getKey().observeEvent(this, cityList -> {
            if (cityList.size() != 0) {
                for (int i = 0; i <= 0; i++) {
                    City city = cityList.get(i);
                    String key = city.getKey();
                    cityName = city.getLocalizedName();
                    viewModel.weatherData(key);
                }
            } else {
                Toast.makeText(this, R.string.nothing_found, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getResponse().observeEvent(this, forecastBigResponse -> {
            for (DailyForecast response : forecastBigResponse.forecast) {
                adapter.setData(forecastBigResponse.forecast);
                if (weatherToday.equals("")) {
                    weatherToday = response.weather.getPhrase();
                }
            }
            tvToday.setVisibility(View.VISIBLE);
            youTubeViewModel.getId(weatherToday + " " + cityName);
        });

        youTubeViewModel.getVideoItem().observeEvent(this, items -> {
            for (Items item : items) {
                if (mPlayer != null) {
                    mPlayer.release();
                }
                youTubePlayerFragment.initialize(Const.Api.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                        youTubePlayer.loadVideo(item.id.getVideoId());
                        if (youTubePlayer.isPlaying()) {
                            youTubePlayer.next();
                        }

                        mPlayer = youTubePlayer;
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
            }
        });

        setupRecyclerView();
    }

    public void setupRecyclerView() {
        adapter = new WeatherListAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvForecast.setLayoutManager(layoutManager);
        rvForecast.setAdapter(adapter);
    }

    @OnClick(R.id.btn_get_weather)
    public void getWeather() {
        String wholeLocation = etLocation.getText().toString();
        viewModel.cityKey(wholeLocation);
    }
}
