package com.weather.application.weather.application;

import com.weather.application.config.NotFoundDataException;
import com.weather.application.weather.doamin.Weather;
import com.weather.application.weather.doamin.WeatherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.lenient;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @InjectMocks
    WeatherServiceImpl weatherServiceImpl;

    @Mock
    WeatherRepository weatherRepository;
    private final int teatYear = 2010;


    @Nested
    @DisplayName("데이터 받아오기(Year) 테스트")
    class year {
        private final int count = 30;

        private double yearTempAug;
        private double yearTempMaxAug;
        private double yearTempMinAug;
        private double yearPrecipitationAug;
        private double yearWindSpeedAug;
        private double yearHumidityAug;

        @BeforeEach
        void before() {
            List<Weather> weathersList = new ArrayList<>();

            int tempSum = 0;
            int tempMaxSum = 0;
            int tempMinSum = 0;

            int precipitationSum = 0;
            int windSpeedSum = 0;
            int humiditySum = 0;

            final Random random = new Random();

            for(int i = 0; i < count; i++) {
                int temp = random.nextInt(30);
                int tempMax = random.nextInt(30);
                int tempMin = random.nextInt(30);

                int precipitation = random.nextInt(5);
                int windSpeed = random.nextInt(5);
                int humidity = random.nextInt(100);

                weathersList.add(Weather.builder()
                        .temp(temp)
                        .tempMax(tempMax)
                        .tempMin(tempMin)

                        .precipitation(precipitation)
                        .windSpeed(windSpeed)
                        .humidity(humidity)

                        .build());

                tempSum += temp;
                tempMaxSum += tempMax;
                tempMinSum += tempMin;

                precipitationSum += precipitation;
                windSpeedSum += windSpeed;
                humiditySum += humidity;
            }

            yearTempAug = makeAug(tempSum);
            yearTempMaxAug = makeAug(tempMaxSum);
            yearTempMinAug = makeAug(tempMinSum);

            yearPrecipitationAug = makeAug(precipitationSum);
            yearWindSpeedAug = makeAug(windSpeedSum);
            yearHumidityAug = makeAug(humiditySum);

            for(int i = 1; i <= 12; i++) {
                lenient().when(weatherRepository.findAllByYearAndMonth(teatYear, i)).thenReturn(weathersList);
            }
        }

        double makeAug(int sumData) {
            return (double) (sumData * 12) / (count * 12);
        }

        @Test
        @DisplayName("온도 테스트")
        void test1() {
            Double result = weatherServiceImpl.year(teatYear, DataTypeIdentifier.TEMP.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearTempAug);
        }

        @Test
        @DisplayName("최고 온도 테스트")
        void test2() {
            Double result = weatherServiceImpl.year(teatYear, DataTypeIdentifier.TEMPMAX.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearTempMaxAug);
        }

        @Test
        @DisplayName("최저 온도 테스트")
        void test3() {
            Double result = weatherServiceImpl.year(teatYear, DataTypeIdentifier.TEMPMIN.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearTempMinAug);
        }

        @Test
        @DisplayName("강수량 테스트")
        void test4() {
            Double result = weatherServiceImpl.year(teatYear, DataTypeIdentifier.PRECIPITATION.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearPrecipitationAug);
        }

        @Test
        @DisplayName("풍속 테스트")
        void test5() {
            Double result = weatherServiceImpl.year(teatYear, DataTypeIdentifier.WINDSPEED.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearWindSpeedAug);
        }

        @Test
        @DisplayName("습도 테스트")
        void test6() {
            Double result = weatherServiceImpl.year(teatYear, DataTypeIdentifier.HUMIDITY.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearHumidityAug);
        }

        @Test
        @DisplayName("데이터 타입이 없을 경우 테스트")
        void test7() {
            assertThrows(NotFoundDataException.class, () -> weatherServiceImpl.year(teatYear, null));
        }

        @Test
        @DisplayName("타입이 잘못되었을 경우 테스트")
        void test8() {
            assertThrows(NotFoundDataException.class, () -> weatherServiceImpl.year(teatYear, "mini0192"));
        }
    }

    @Nested
    @DisplayName("데이터 받아오기(Month) 테스트")
    class month {
        private final int count = 30;
        private final int month = 1;

        private double yearTempAug;
        private double yearTempMaxAug;
        private double yearTempMinAug;
        private double yearPrecipitationAug;
        private double yearWindSpeedAug;
        private double yearHumidityAug;

        @BeforeEach
        void before() {
            List<Weather> weathersList = new ArrayList<>();

            int tempSum = 0;
            int tempMaxSum = 0;
            int tempMinSum = 0;

            int precipitationSum = 0;
            int windSpeedSum = 0;
            int humiditySum = 0;

            final Random random = new Random();

            for(int i = 0; i < count; i++) {
                int temp = random.nextInt(30);
                int tempMax = random.nextInt(30);
                int tempMin = random.nextInt(30);

                int precipitation = random.nextInt(5);
                int windSpeed = random.nextInt(5);
                int humidity = random.nextInt(100);

                weathersList.add(Weather.builder()
                        .temp(temp)
                        .tempMax(tempMax)
                        .tempMin(tempMin)

                        .precipitation(precipitation)
                        .windSpeed(windSpeed)
                        .humidity(humidity)

                        .build());

                tempSum += temp;
                tempMaxSum += tempMax;
                tempMinSum += tempMin;

                precipitationSum += precipitation;
                windSpeedSum += windSpeed;
                humiditySum += humidity;
            }

            yearTempAug = makeAug(tempSum);
            yearTempMaxAug = makeAug(tempMaxSum);
            yearTempMinAug = makeAug(tempMinSum);

            yearPrecipitationAug = makeAug(precipitationSum);
            yearWindSpeedAug = makeAug(windSpeedSum);
            yearHumidityAug = makeAug(humiditySum);

            lenient().when(weatherRepository.findAllByYearAndMonth(teatYear, month)).thenReturn(weathersList);
        }

        double makeAug(int sumData) {
            return (double) sumData / count;
        }

        @Test
        @DisplayName("온도 테스트")
        void test1() {
            Double result = weatherServiceImpl.month(teatYear, month, DataTypeIdentifier.TEMP.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearTempAug);
        }

        @Test
        @DisplayName("최고 온도 테스트")
        void test2() {
            Double result = weatherServiceImpl.month(teatYear, month, DataTypeIdentifier.TEMPMAX.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearTempMaxAug);
        }

        @Test
        @DisplayName("최저 온도 테스트")
        void test3() {
            Double result = weatherServiceImpl.month(teatYear, month, DataTypeIdentifier.TEMPMIN.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearTempMinAug);
        }

        @Test
        @DisplayName("강수량 테스트")
        void test4() {
            Double result = weatherServiceImpl.month(teatYear, month, DataTypeIdentifier.PRECIPITATION.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearPrecipitationAug);
        }

        @Test
        @DisplayName("풍속 테스트")
        void test5() {
            Double result = weatherServiceImpl.month(teatYear, month, DataTypeIdentifier.WINDSPEED.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearWindSpeedAug);
        }

        @Test
        @DisplayName("습도 테스트")
        void test6() {
            Double result = weatherServiceImpl.month(teatYear, month, DataTypeIdentifier.HUMIDITY.getIdentifier());
            Assertions.assertThat(result).isEqualTo(yearHumidityAug);
        }

        @Test
        @DisplayName("데이터 타입이 없을 경우 테스트")
        void test7() {
            assertThrows(NotFoundDataException.class, () -> weatherServiceImpl.month(teatYear, month, null));
        }

        @Test
        @DisplayName("타입이 잘못되었을 경우 테스트")
        void test8() {
            assertThrows(NotFoundDataException.class, () -> weatherServiceImpl.month(teatYear, month, "mini0192"));
        }
    }

    @Nested
    @DisplayName("데이터 받아오기(Day) 테스트")
    class day {
        private final int count = 30;
        private final int month = 1;

        List<Double> tempList = new ArrayList<>();
        List<Double> tempMaxList = new ArrayList<>();
        List<Double> tempMinList = new ArrayList<>();


        List<Double> precipitationList = new ArrayList<>();
        List<Double> windSpeedList = new ArrayList<>();
        List<Double> humidityList = new ArrayList<>();

        @BeforeEach
        void before() {
            List<Weather> weathersList = new ArrayList<>();

            final Random random = new Random();

            for(int i = 0; i < count; i++) {
                int temp = random.nextInt(30);
                int tempMax = random.nextInt(30);
                int tempMin = random.nextInt(30);

                int precipitation = random.nextInt(5);
                int windSpeed = random.nextInt(5);
                int humidity = random.nextInt(100);

                weathersList.add(Weather.builder()
                        .temp(temp)
                        .tempMax(tempMax)
                        .tempMin(tempMin)

                        .precipitation(precipitation)
                        .windSpeed(windSpeed)
                        .humidity(humidity)

                        .build());

                tempList.add((double)temp);
                tempMaxList.add((double)tempMax);
                tempMinList.add((double)tempMin);

                precipitationList.add((double)precipitation);
                windSpeedList.add((double)windSpeed);
                humidityList.add((double)humidity);
            }

            lenient().when(weatherRepository.findAllByYearAndMonth(teatYear, month)).thenReturn(weathersList);
        }

        double makeAug(int sumData) {
            return (double) sumData / count;
        }

        @Test
        @DisplayName("온도 테스트")
        void test1() {
            List<Double> result = weatherServiceImpl.day(teatYear, month, DataTypeIdentifier.TEMP.getIdentifier());
            Assertions.assertThat(result.size()).isEqualTo(tempList.size());
            for(int i = 0; i < result.size(); i++) {
                Assertions.assertThat(result.get(i)).isEqualTo(tempList.get(i));
            }
        }

        @Test
        @DisplayName("최고 온도 테스트")
        void test2() {
            List<Double> result = weatherServiceImpl.day(teatYear, month, DataTypeIdentifier.TEMPMAX.getIdentifier());
            Assertions.assertThat(result.size()).isEqualTo(tempMaxList.size());
            for(int i = 0; i < result.size(); i++) {
                Assertions.assertThat(result.get(i)).isEqualTo(tempMaxList.get(i));
            }
        }

        @Test
        @DisplayName("최저 온도 테스트")
        void test3() {
            List<Double> result = weatherServiceImpl.day(teatYear, month, DataTypeIdentifier.TEMPMIN.getIdentifier());
            Assertions.assertThat(result.size()).isEqualTo(tempMinList.size());
            for(int i = 0; i < result.size(); i++) {
                Assertions.assertThat(result.get(i)).isEqualTo(tempMinList.get(i));
            }
        }

        @Test
        @DisplayName("강수량 테스트")
        void test4() {
            List<Double> result = weatherServiceImpl.day(teatYear, month, DataTypeIdentifier.PRECIPITATION.getIdentifier());
            Assertions.assertThat(result.size()).isEqualTo(precipitationList.size());
            for(int i = 0; i < result.size(); i++) {
                Assertions.assertThat(result.get(i)).isEqualTo(precipitationList.get(i));
            }
        }

        @Test
        @DisplayName("풍속 테스트")
        void test5() {
            List<Double> result = weatherServiceImpl.day(teatYear, month, DataTypeIdentifier.WINDSPEED.getIdentifier());
            Assertions.assertThat(result.size()).isEqualTo(windSpeedList.size());
            for(int i = 0; i < result.size(); i++) {
                Assertions.assertThat(result.get(i)).isEqualTo(windSpeedList.get(i));
            }
        }

        @Test
        @DisplayName("습도 테스트")
        void test6() {
            List<Double> result = weatherServiceImpl.day(teatYear, month, DataTypeIdentifier.HUMIDITY.getIdentifier());
            Assertions.assertThat(result.size()).isEqualTo(humidityList.size());
            for(int i = 0; i < result.size(); i++) {
                Assertions.assertThat(result.get(i)).isEqualTo(humidityList.get(i));
            }
        }

        @Test
        @DisplayName("데이터 타입이 없을 경우 테스트")
        void test7() {
            assertThrows(NotFoundDataException.class, () -> weatherServiceImpl.month(teatYear, month, null));
        }

        @Test
        @DisplayName("타입이 잘못되었을 경우 테스트")
        void test8() {
            assertThrows(NotFoundDataException.class, () -> weatherServiceImpl.month(teatYear, month, "mini0192"));
        }
    }
}