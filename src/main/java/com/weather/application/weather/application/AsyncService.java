package com.weather.application.weather.application;

import com.weather.application.weather.domain.cache.WeatherTableCache;
import com.weather.application.weather.presentation.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {
    private final CrudRepository<WeatherTableCache, String> weatherTableCacheRepository;

    @Async
    public void caching(String id, WeatherResponse.Data dto) {
        weatherTableCacheRepository.save(WeatherTableCache.builder()
                .id(id)
                .weatherTable(dto)
                .build());
    }

}
