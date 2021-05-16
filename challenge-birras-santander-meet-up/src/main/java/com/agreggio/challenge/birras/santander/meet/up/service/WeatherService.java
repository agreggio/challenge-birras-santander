package com.agreggio.challenge.birras.santander.meet.up.service;


import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.meet.up.dto.weather.WeatherResponseDto;

public interface WeatherService {

    /**
     * Call the weather service and get the temperature
     *
     * @return {@link WeatherResponseDto}
     */
    WeatherResponseDto getWeather() throws ServiceException;

}
