package com.weather.application.weather.presentation;

import com.weather.application.weather.application.DataTypeIdentifier;
import com.weather.application.weather.application.WeatherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @MockBean
    WeatherService weatherService;

    @Autowired
    MockMvc mockMvc;
    String globalUrl = "/api/v1/weather";

    String domainTemp = DataTypeIdentifier.TEMP.getIdentifier();

    String identityYear = DataIdentifier.YEAR.getIdentifier();
    String identityMonth = DataIdentifier.MONTH.getIdentifier();
    String identityDay = DataIdentifier.DAY.getIdentifier();

    int testYear = 2024;
    int testMonth = 1;


    @Nested
    @DisplayName("엔드포인트 테스트")
    class getTemp {
        private final String domain = DataTypeIdentifier.TEMP.getIdentifier();
        private final String url = globalUrl + "/" + domain;
        @Test
        @DisplayName("잘못된 식별자 테스트")
        void test1() throws Exception {
            String giveIdentifier = "identifier=" + "test";
            String giveYear = "year=" + testYear;
            String giveData = "?" + giveIdentifier + "&" + giveYear;

            mockMvc.perform(get(url + giveData))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("잘못된 년도 테스트")
        void test2() throws Exception {
            String giveIdentifier = "identifier=" + identityYear;
            String giveYear = "year=" + "test";
            String giveData = "?" + giveIdentifier + "&" + giveYear;

            mockMvc.perform(get(url + giveData))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("빈 입력 테스트")
        void test3() throws Exception {
            mockMvc.perform(get(url))
                    .andExpect(status().isBadRequest());
        }

        @Nested
        @DisplayName("식별자 year 테스트")
        class year {
            @Test
            @DisplayName("정상 테스트")
            void test1() throws Exception {

                String giveIdentifier = "identifier=" + identityYear;
                String giveYear = "year=" + testYear;
                String giveData = "?" + giveIdentifier + "&" + giveYear;

                mockMvc.perform(get(url + giveData))
                        .andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("식별자 month 테스트")
        class month {
            @Test
            @DisplayName("정상 테스트")
            void test1() throws Exception {
                String giveIdentifier = "identifier=" + identityMonth;
                String giveYear = "year=" + testYear;
                String giveMonth = "month=" + testMonth;
                String giveData = "?" + giveIdentifier + "&" + giveYear + "&" + giveMonth;

                mockMvc.perform(get(url + giveData))
                        .andExpect(status().isOk());
            }

            @Test
            @DisplayName("month 누락 테스트")
            void test2() throws Exception {
                String giveIdentifier = "identifier=" + identityMonth;
                String giveYear = "year=" + testYear;
                String giveData = "?" + giveIdentifier + "&" + giveYear;

                mockMvc.perform(get(url + giveData))
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("식별자 day 테스트")
        class day {
            @Test
            @DisplayName("정상 테스트")
            void test1() throws Exception {
                String giveIdentifier = "identifier=" + identityDay;
                String giveYear = "year=" + testYear;
                String giveMonth = "month=" + testMonth;
                String giveData = "?" + giveIdentifier + "&" + giveYear + "&" + giveMonth;

                mockMvc.perform(get(url + giveData))
                        .andExpect(status().isOk());
            }

            @Test
            @DisplayName("month 누락 테스트")
            void test2() throws Exception {
                String giveIdentifier = "identifier=" + identityDay;
                String giveYear = "year=" + testYear;
                String giveData = "?" + giveIdentifier + "&" + giveYear;

                mockMvc.perform(get(url + giveData))
                        .andExpect(status().isBadRequest());
            }
        }
    }
}