package com.weather.application.weather.application;

import com.weather.application.config.NotFoundDataException;
import com.weather.application.config.RunTimer;
import com.weather.application.weather.doamin.Weather;
import com.weather.application.weather.doamin.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final Map<Integer, Double> yearCash = new HashMap<>();

    @Override
    @RunTimer(method = "특정 년도 데이터 받아오기")
    public Double year(int year, String dataType) {
        double sum = 0;
        int count = 0;
        for(int month = 1; month <= 12; month++) {
            List<Weather> savedData = weatherRepository.findAllByYearAndMonth(year, month);
            for(Weather data : savedData) {
                sum += getDataByIdentifier(data, dataType);
                count++;
            }
        }
        sum /= count;
        return sum;
    }

    @Override
    @RunTimer(method = "특정 년도의 특정 달 데이터 받아오기")
    public Double month(int year, int month, String dataType) {
        double sum = 0;
        int count = 0;
        List<Weather> savedData = weatherRepository.findAllByYearAndMonth(year, month);
        for(Weather data : savedData) {
            sum += getDataByIdentifier(data, dataType);
            count++;
        }
        sum /= count;
        return sum;
    }

    @Override
    @RunTimer(method = "특정 년도의 특정 달 전체 데이터 받아오기")
    public List<Double> day(int year, int month, String dataType) {
        List<Weather> savedData = weatherRepository.findAllByYearAndMonth(year, month);
        List<Double> rtn = savedData.stream().map(data -> Double.valueOf(getDataByIdentifier(data, dataType))).toList();
        return rtn;
    }

    private Integer getDataByIdentifier(Weather data, String dataType) {
        if(dataType != null) {
            if(dataType.equals(DataTypeIdentifier.TEMP.getIdentifier())) return data.getTemp();
            if(dataType.equals(DataTypeIdentifier.TEMPMAX.getIdentifier())) return data.getTempMax();
            if(dataType.equals(DataTypeIdentifier.TEMPMIN.getIdentifier())) return data.getTempMin();

            if(dataType.equals(DataTypeIdentifier.PRECIPITATION.getIdentifier())) return data.getPrecipitation();
            if(dataType.equals(DataTypeIdentifier.WINDSPEED.getIdentifier())) return data.getWindSpeed();
            if(dataType.equals(DataTypeIdentifier.HUMIDITY.getIdentifier())) return data.getHumidity();
        }
        throw new NotFoundDataException("asd");
    }
}
