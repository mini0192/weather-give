package com.weather.application.weather.doamin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    List<Weather> findAllByYearAndMonth(int year, int month);
}
