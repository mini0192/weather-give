package com.weather.application.weather.domain;

import com.weather.application.weather.presentation.dto.WeatherResponse;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "WeatherTable", timeToLive = 300)
public class WeatherTableCache {
    @Id
    String id;
    WeatherResponse.Data weatherTable;
}
