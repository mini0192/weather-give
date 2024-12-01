package com.weather.application.config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeConfig {
    private TimeConfig() {}

    public static long stringToUnixTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dateString, formatter);

        Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return instant.getEpochSecond() + 32400L;
    }

    public static String unixTimeToString(Long unixTime) {
        Instant instant = Instant.ofEpochSecond(unixTime);
        LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return date.format(formatter);
    }
}
