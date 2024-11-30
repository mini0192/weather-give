package com.weather.application.weather.presentation;

import com.weather.application.config.RunTimer;
import com.weather.application.weather.application.WeatherService;
import com.weather.application.weather.presentation.dto.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @RunTimer
    @GetMapping
    public ResponseEntity<List<WeatherResponse.Data>> getData(@RequestParam("start") String start,
                                                              @RequestParam("end") String end) {
        return ResponseEntity.ok().body(weatherService.getData(start, end));
    }
}
