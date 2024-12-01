package com.weather.application.weather.domain;

import org.springframework.data.repository.CrudRepository;

public interface WeatherTableCacheRepository extends CrudRepository<WeatherTableCache, String> {
}
