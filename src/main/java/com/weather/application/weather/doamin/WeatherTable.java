package com.weather.application.weather.doamin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "weather_table",
    indexes = {
            @Index(name = "idx_date", columnList = "date")
    }
)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date")
    Long date;

    @Column(name = "avg_co2_ppm")
    Double avgCo2Ppm;

    @Column(name = "avg_ch4_ppb")
    Double avgCh4Ppb;

    @Column(name = "avg_n2o_ppb")
    Double avgN2oPpb;

    @Column(name = "avg_cfc11_ppt")
    Double avgCfc11Ppt;

    @Column(name = "avg_cfc12_ppt")
    Double avgCfc12Ppt;

    @Column(name = "avg_cfc113_ppt")
    Double avgCfc113Ppt;

    @Column(name = "avg_sf6_ppt")
    Double avgSf6Ppt;
}
