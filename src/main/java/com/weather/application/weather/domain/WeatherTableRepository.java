package com.weather.application.weather.domain;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WeatherTableRepository extends JpaRepository<WeatherTable, Integer> {
    @Query("SELECT w from WeatherTable w WHERE w.date BETWEEN :start AND :end")
    List<WeatherTable> findByStartAndEndDate(@Param("start") long start, @Param("end") long end);
    Optional<WeatherTable> findByDate(long date);
}