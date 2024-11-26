package com.weather.application.weather.doamin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weather_table")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer temp;
    private Integer tempMax;
    private Integer tempMin;
    private Integer precipitation;
    private Integer windSpeed;
    private Integer humidity;
}
