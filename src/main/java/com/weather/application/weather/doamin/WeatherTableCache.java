package com.weather.application.weather.doamin;

import com.weather.application.weather.presentation.dto.WeatherResponse;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@RedisHash(value = "WeatherTable", timeToLive = 300)
public class WeatherTableCache implements Serializable {
    @Id
    String id;
    List<WeatherResponse.Data> weatherTable;
}
