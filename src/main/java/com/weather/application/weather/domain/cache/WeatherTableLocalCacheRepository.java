package com.weather.application.weather.domain.cache;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Profile("local")
@Repository
public class WeatherTableLocalCacheRepository implements CrudRepository<WeatherTableCache, String> {

    Map<String, WeatherTableCache> cacheMap = new ConcurrentHashMap<>();

    @Override
    public <S extends WeatherTableCache> S save(S entity) {
        String id = entity.getId();
        cacheMap.put(id, entity);
        return entity;
    }

    @Override
    public Optional<WeatherTableCache> findById(String s) {
        return Optional.ofNullable(cacheMap.get(s));
    }

    @Override
    public <S extends WeatherTableCache> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<WeatherTableCache> findAll() {
        return null;
    }

    @Override
    public Iterable<WeatherTableCache> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(WeatherTableCache entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends WeatherTableCache> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
