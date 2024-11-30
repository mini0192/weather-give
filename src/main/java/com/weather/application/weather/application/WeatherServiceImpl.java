package com.weather.application.weather.application;

import com.weather.application.config.TimeConfig;
import com.weather.application.weather.doamin.WeatherTable;
import com.weather.application.weather.doamin.WeatherRepository;
import com.weather.application.weather.doamin.WeatherTableCache;
import com.weather.application.weather.doamin.WeatherTableCacheRepository;
import com.weather.application.weather.presentation.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherTableCacheRepository weatherTableCacheRepository;

    @Override
    public List<WeatherResponse.Data> getData(String start, String end) {
        String cacheId = makeCacheId(start, end);
        Optional<WeatherTableCache> cacheData = weatherTableCacheRepository.findById(cacheId);
        if(cacheData.isPresent()) return cacheData.get().getWeatherTable();

        long startTime = TimeConfig.stringToUnixTime(start);
        long endTime = TimeConfig.stringToUnixTime(end);

        List<WeatherTable> data = weatherRepository.findByStartAndEndDate(startTime, endTime);
        List<WeatherResponse.Data> dataList = data.stream().map(WeatherResponse.Data::toDto).toList();
        weatherTableCacheRepository.save(WeatherTableCache.builder()
                .id(cacheId)
                .weatherTable(dataList)
                .build());
        return data.stream().map(WeatherResponse.Data::toDto).toList();
    }

    private String makeCacheId(String start, String end) {
        return start + "-" + end;
    }
}
