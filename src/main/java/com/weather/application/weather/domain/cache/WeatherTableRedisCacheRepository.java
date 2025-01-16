package com.weather.application.weather.domain.cache;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Profile("redis")
@Repository
public interface WeatherTableRedisCacheRepository extends CrudRepository<WeatherTableCache, String> {
}
