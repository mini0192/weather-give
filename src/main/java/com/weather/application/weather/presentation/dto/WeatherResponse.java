package com.weather.application.weather.presentation.dto;

import com.weather.application.config.TimeConfig;
import com.weather.application.weather.doamin.WeatherTable;
import lombok.Builder;

public class WeatherResponse {
    @Builder
    public record Data (
            String date,
            Double avgCo2Ppm,
            Double avgCh4Ppb,
            Double avgN2oPpb,
            Double avgCfc11Ppt,
            Double avgCfc12Ppt,
            Double avgCfc113Ppt,
            Double avgSf6Ppt
    ) {
        public static Data toDto(WeatherTable entity) {
            return Data.builder()
                    .date(TimeConfig.unixTimeToString(entity.getDate()))
                    .avgCo2Ppm(entity.getAvgCo2Ppm())
                    .avgCh4Ppb(entity.getAvgCh4Ppb())
                    .avgN2oPpb(entity.getAvgN2oPpb())
                    .avgCfc11Ppt(entity.getAvgCfc11Ppt())
                    .avgCfc12Ppt(entity.getAvgCfc12Ppt())
                    .avgCfc113Ppt(entity.getAvgCfc113Ppt())
                    .avgSf6Ppt(entity.getAvgSf6Ppt())
                    .build();
        }
    }
}
