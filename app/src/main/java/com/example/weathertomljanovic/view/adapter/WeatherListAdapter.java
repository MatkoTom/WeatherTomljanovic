package com.example.weathertomljanovic.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathertomljanovic.R;
import com.example.weathertomljanovic.model.forecast.DailyForecast;

import java.util.ArrayList;
import java.util.List;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.CustomViewHolder> {

    private List<DailyForecast> data = new ArrayList<>();

    public void setData(List<DailyForecast> newData) {
        if (newData != null && !newData.isEmpty()) {
            this.data.clear();
            this.data.addAll(newData);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        DailyForecast item = data.get(position);

        holder.tvTempMinimum.setText(String.format("Min: %s %s", item.temperature.minimumTemp.getValue(), item.temperature.minimumTemp.getUnit()));
        holder.tvTempMaximum.setText(String.format("Max: %s %s", item.temperature.maximumTemp.getValue(), item.temperature.maximumTemp.getUnit()));
        String[] date = item.date.split("T");
        holder.tvDate.setText(String.format("Date: %s", date[0]));
        holder.tvForecast.setText(item.weather.getPhrase());

        if (holder.tvForecast.getText().toString().equals("Sunny") || holder.tvForecast.getText().toString().equals("Mostly sunny") || holder.tvForecast.getText().toString().equals("Partly sunny")) {
            holder.ivWeatherIcon.setImageDrawable(holder.ivWeatherIcon.getContext().getDrawable(R.drawable.sunny));
        } else if (holder.tvForecast.getText().toString().equals("Intermittent clouds") || holder.tvForecast.getText().toString().equals("Mostly cloudy")) {
            holder.ivWeatherIcon.setImageDrawable(holder.ivWeatherIcon.getContext().getDrawable(R.drawable.cloudy2));
        } else if (holder.tvForecast.getText().toString().equals("Hazy sunshine") || holder.tvForecast.getText().toString().equals("Fog")) {
            holder.ivWeatherIcon.setImageDrawable(holder.ivWeatherIcon.getContext().getDrawable(R.drawable.fog));
        } else if (holder.tvForecast.getText().toString().equals("Cloudy") || holder.tvForecast.getText().toString().equals("Dreary (Overcast)")) {
            holder.ivWeatherIcon.setImageDrawable(holder.ivWeatherIcon.getContext().getDrawable(R.drawable.overcast));
        } else if (holder.tvForecast.getText().toString().equals("Showers") || holder.tvForecast.getText().toString().equals("Mostly cloudy w/ showers") || holder.tvForecast.getText().toString().equals("Partly sunny w/ showers") || holder.tvForecast.getText().toString().equals("Rain")) {
            holder.ivWeatherIcon.setImageDrawable(holder.ivWeatherIcon.getContext().getDrawable(R.drawable.shower3));
        } else if (holder.tvForecast.getText().toString().equals("Snow") || holder.tvForecast.getText().toString().equals("Ice") || holder.tvForecast.getText().toString().equals("Sleet") || holder.tvForecast.getText().toString().equals("Rain and snow")) {
            holder.ivWeatherIcon.setImageDrawable(holder.ivWeatherIcon.getContext().getDrawable(R.drawable.snow4));
        } else {
            holder.ivWeatherIcon.setImageDrawable(holder.ivWeatherIcon.getContext().getDrawable(R.drawable.tstorm1));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvTempMinimum;
        TextView tvTempMaximum;
        TextView tvForecast;
        TextView tvDate;
        ImageView ivWeatherIcon;
        ConstraintLayout rowParentLayout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTempMinimum = itemView.findViewById(R.id.tv_temp_min);
            tvTempMaximum = itemView.findViewById(R.id.tv_temp_max);
            tvForecast = itemView.findViewById(R.id.tv_forecast);
            tvDate = itemView.findViewById(R.id.tv_date);
            ivWeatherIcon = itemView.findViewById(R.id.iv_weather_image);
            rowParentLayout = itemView.findViewById(R.id.row_parent_layout);
        }
    }
}
