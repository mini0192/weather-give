package com.weather.application.weather.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataIdentifier {
    YEAR("year"),
    MONTH("month"),
    DAY("day");
    private final String identifier;
}
