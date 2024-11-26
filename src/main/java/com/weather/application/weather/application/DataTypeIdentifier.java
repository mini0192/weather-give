package com.weather.application.weather.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataTypeIdentifier {
    /*
    private Integer tempMin;
    private Integer precipitation;
    private Integer windSpeed;
    private Integer humidity;
     */
    TEMP("temp"),
    TEMPMAX("temp_max"),
    TEMPMIN("temp_min"),

    PRECIPITATION("precipitation"),
    WINDSPEED("wind_speed"),
    HUMIDITY("humidity");

    private final String identifier;
}
