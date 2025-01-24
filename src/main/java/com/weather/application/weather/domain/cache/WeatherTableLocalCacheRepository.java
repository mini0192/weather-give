package com.weather.application.weather.domain.cache;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Profile("local")
@Repository
public class WeatherTableLocalCacheRepository implements WeatherTableCacheRepository {

    Map<String, WeatherTableCache> cacheMap = new ConcurrentHashMap<>();

    @Override
    public void save(WeatherTableCache entity) {
        String id = entity.getId();
        cacheMap.put(id, entity);
    }

    @Override
    public Optional<WeatherTableCache> findById(String s) {
        return Optional.ofNullable(cacheMap.get(s));
    }
}
