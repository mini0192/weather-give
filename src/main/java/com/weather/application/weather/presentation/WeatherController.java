package com.weather.application.weather.presentation;

import com.weather.application.weather.application.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{data_type}")
    public ResponseEntity<List<Double>> getTemp(@PathVariable("data_type") String dataType,
                                                @RequestParam("identifier") String identifier,
                                                @RequestParam("year") int year,
                                                @RequestParam(value = "month", defaultValue = "0") int month) {
        if(DataIdentifier.YEAR.getIdentifier().equals(identifier)) {
            List<Double> rtn = List.of(weatherService.year(year, dataType));
            return new ResponseEntity<>(rtn, HttpStatus.OK);
        }

        if(DataIdentifier.MONTH.getIdentifier().equals(identifier)) {
            if(month == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            List<Double> rtn = List.of(weatherService.month(year, month, dataType));
            return new ResponseEntity<>(rtn, HttpStatus.OK);
        }

        if(DataIdentifier.DAY.getIdentifier().equals(identifier)) {
            if(month == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            List<Double> rtn = weatherService.day(year, month, dataType);
            return new ResponseEntity<>(rtn, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
