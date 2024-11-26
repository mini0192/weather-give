package com.weather.application.weather.application;

import java.util.List;

public interface WeatherService {
    Double year(int year, String dataType);
    Double month(int year, int month, String dataType);
    List<Double> day(int year, int month, String dataType);
}
