package com.weather.application.weather.domain.cache;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface WeatherTableCacheRepository {
    void save(WeatherTableCache entity);
    Optional<WeatherTableCache> findById(String date);
}
