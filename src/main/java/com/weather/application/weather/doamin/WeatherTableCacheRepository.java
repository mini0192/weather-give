package com.weather.application.weather.doamin;

import org.springframework.data.repository.CrudRepository;

public interface WeatherTableCacheRepository extends CrudRepository<WeatherTableCache, String> {
}
