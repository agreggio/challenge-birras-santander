package com.agreggio.challenge.birras.santander.meet.up.manager;

import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.meet.up.controller.MeetUpController;
import com.agreggio.challenge.birras.santander.meet.up.dto.weather.WeatherDto;
import com.agreggio.challenge.birras.santander.meet.up.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public interface WeatherCacheManager {

    /**
     * Create a cache weather
     *
     */
    void createCacheWeather();

    /**
     * Refresh cache
     *
     */
    void refresh();

    /**
     * Call the weather service and get the temperature
     *
     * @param weatherDate {@link Date}
     * @return {@link Double}
     */
    Double getTemperatureByDate(Date weatherDate) throws ServiceException;

}
