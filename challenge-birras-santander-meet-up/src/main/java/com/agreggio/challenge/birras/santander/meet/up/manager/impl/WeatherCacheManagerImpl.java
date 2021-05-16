package com.agreggio.challenge.birras.santander.meet.up.manager.impl;

import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.meet.up.controller.MeetUpController;
import com.agreggio.challenge.birras.santander.meet.up.dto.weather.WeatherDto;
import com.agreggio.challenge.birras.santander.meet.up.manager.WeatherCacheManager;
import com.agreggio.challenge.birras.santander.meet.up.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class WeatherCacheManagerImpl implements WeatherCacheManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetUpController.class);

    @Autowired
    WeatherService weatherService;

    private Map<Date, Double> temperatureDayMap = new HashMap<>();


    @PostConstruct
    @Cacheable("weather")
    @Override
    public void createCacheWeather() throws ServiceException {

        LOGGER.info("Create cache weather");

        temperatureDayMap = weatherService.getWeather().getWeatherDtoList().stream().collect(Collectors.toMap(
                WeatherDto::getDate , weatherDto -> weatherDto.getTemperatureDay().getDay()));

        LOGGER.info("Cache data {} ", temperatureDayMap);

    }

    @Scheduled(fixedRateString = "${fidex.rate}")
    @Override
    public void refresh() throws ServiceException {

        LOGGER.info("Refresh cache weather");

        createCacheWeather();
    }


    @Override
    public Double getTemperatureByDate(Date weatherDate) {

        LOGGER.info("Get temperature by date: " + weatherDate);

        return temperatureDayMap.get(weatherDate);

    }

}
