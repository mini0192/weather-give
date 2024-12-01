package com.weather.application.weather.application;

import com.weather.application.config.TimeConfig;
import com.weather.application.config.exception.NotFoundDataException;
import com.weather.application.weather.domain.WeatherTable;
import com.weather.application.weather.domain.WeatherTableCache;
import com.weather.application.weather.domain.WeatherTableRepository;
import com.weather.application.weather.domain.WeatherTableCacheRepository;
import com.weather.application.weather.presentation.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherTableRepository weatherTableRepository;
    private final WeatherTableCacheRepository weatherTableCacheRepository;
    private final AsyncService asyncService;

    @Override
    public List<WeatherResponse.Data> getListData(String start, String end) {
        long startTime = TimeConfig.stringToUnixTime(start);
        long endTime = TimeConfig.stringToUnixTime(end);

        List<WeatherTable> data = weatherTableRepository.findByStartAndEndDate(startTime, endTime);
        return data.stream().map(WeatherResponse.Data::toDto).toList();
    }

    @Override
    public WeatherResponse.Data getData(String day) {
        Optional<WeatherTableCache> dataOptional = weatherTableCacheRepository.findById(day);
        if(dataOptional.isPresent()) return dataOptional.get().getWeatherTable();

        long dayTime = TimeConfig.stringToUnixTime(day);
        WeatherTable data = weatherTableRepository.findByDate(dayTime).orElseThrow(() -> new NotFoundDataException("데이터가 존재하지 않습니다."));
        WeatherResponse.Data dto = WeatherResponse.Data.toDto(data);
        asyncService.caching(day, dto);
        return dto;
    }
}
