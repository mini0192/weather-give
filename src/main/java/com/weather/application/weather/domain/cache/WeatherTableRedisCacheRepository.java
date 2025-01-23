package com.weather.application.weather.domain.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile("redis")
@Repository
@RequiredArgsConstructor
public class WeatherTableRedisCacheRepository implements WeatherTableCacheRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(WeatherTableCache entity) {
        redisTemplate.opsForValue().set(entity.getId(), entity);
    }

    @Override
    public Optional<WeatherTableCache> findById(String date) {
        return Optional.ofNullable((WeatherTableCache) redisTemplate.opsForValue().get(date));
    }
}
