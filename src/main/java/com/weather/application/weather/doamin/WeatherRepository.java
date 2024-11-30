package com.weather.application.weather.doamin;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherTable, Integer> {
    @Query("SELECT w from WeatherTable w WHERE w.date BETWEEN :start AND :end")
    List<WeatherTable> findByStartAndEndDate(@Param("start") long start, @Param("end") long end);
}
