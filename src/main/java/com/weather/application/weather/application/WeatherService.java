package com.weather.application.weather.application;

import com.weather.application.weather.presentation.dto.WeatherResponse;

import java.util.List;

public interface WeatherService {
    List<WeatherResponse.Data> getListData(String start, String end);
    WeatherResponse.Data getData(String day);
}
