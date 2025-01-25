package com.weather.application.weather.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(
    name = "weather_table"
//    indexes = {
//        @Index(name = "idx_date", columnList = "date")
//    }
)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false, unique = true)
    private Long date;

    @Column(name = "avg_co2_ppm")
    private Double avgCo2Ppm;

    @Column(name = "avg_ch4_ppb")
    private Double avgCh4Ppb;

    @Column(name = "avg_n2o_ppb")
    private Double avgN2oPpb;

    @Column(name = "avg_cfc11_ppt")
    private Double avgCfc11Ppt;

    @Column(name = "avg_cfc12_ppt")
    private Double avgCfc12Ppt;

    @Column(name = "avg_cfc113_ppt")
    private Double avgCfc113Ppt;

    @Column(name = "avg_sf6_ppt")
    private Double avgSf6Ppt;
}
