package com.weather.application.weather.presentation;

import com.weather.application.config.RunTimer;
import com.weather.application.weather.application.WeatherService;
import com.weather.application.weather.presentation.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/weathers")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @RunTimer(method = "날짜 범위 검색")
    @GetMapping
    public ResponseEntity<List<WeatherResponse.Data>> getData(@RequestParam("start") String start,
                                                              @RequestParam("end") String end) {
        return ResponseEntity.ok().body(weatherService.getListData(start, end));
    }

    @RunTimer(method = "날짜 검색")
    @GetMapping("/details")
    public ResponseEntity<WeatherResponse.Data> getData(@RequestParam("day") String day) {
        return ResponseEntity.ok().body(weatherService.getData(day));
    }
}
