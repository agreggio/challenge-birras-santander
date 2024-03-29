package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import java.net.URI;


import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.meet.up.dto.weather.WeatherResponseDto;
import com.agreggio.challenge.birras.santander.meet.up.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Value("${url.weather}")
    private String WEATHER_URL;

    @Value("${host.weather}")
    private String HOST;

    @Value("${key.weather}")
    private String KEY;

	@Override
    @Cacheable("weather_service")
    public WeatherResponseDto getWeather() throws ServiceException {

        LOGGER.info("Calling weather service ");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-host", HOST);
        headers.set("x-rapidapi-key", KEY);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("", headers);

        ResponseEntity<WeatherResponseDto> result;

        try {

            result = restTemplate.exchange(URI.create(WEATHER_URL), HttpMethod.GET, request, WeatherResponseDto.class);

        }catch(RestClientException e){
            throw new ServiceException("Error al obtener temperatura " + e.getMessage());
        }


        return result.getBody();

    }

}
