package com.weather.application.weather.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WeatherTableRepository {
    private final EntityManager em;

    public Optional<WeatherTable> findByDate(Long date) {
        try {
            WeatherTable result = em.createQuery("SELECT w FROM WeatherTable w WHERE w.date = :date", WeatherTable.class)
                    .setParameter("date", date)
                    .getSingleResult();
            return Optional.ofNullable(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<WeatherTable> findByStartAndEndDate(long start, long end) {
        return em.createQuery("SELECT w from WeatherTable w WHERE w.date BETWEEN :startDate AND :endDate", WeatherTable.class)
                .setParameter("startDate", start)
                .setParameter("endDate", end)
                .getResultList();
    }
}