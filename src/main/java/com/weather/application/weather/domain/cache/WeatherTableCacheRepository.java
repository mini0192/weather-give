package com.weather.application.weather.domain.cache;

import org.springframework.data.repository.CrudRepository;

public interface WeatherTableCacheRepository extends CrudRepository<WeatherTableCache, String> {
}
