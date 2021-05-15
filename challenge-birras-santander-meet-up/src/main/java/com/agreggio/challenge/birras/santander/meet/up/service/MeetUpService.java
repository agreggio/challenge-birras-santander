package com.agreggio.challenge.birras.santander.meet.up.service;

import com.agreggio.challenge.birras.santander.common.entitie.MeetUp;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.meet.up.dto.AmountBeerDto;
import com.agreggio.challenge.birras.santander.meet.up.dto.MeetUpDto;
import com.agreggio.challenge.birras.santander.meet.up.dto.WeatherMeetUpDto;

public interface MeetUpService {

    /**
     * Find a MeetUp by Id
     *
     * @param meetUpId long
     * @return {@link MeetUp}
     */
    MeetUp findMeetUp(long meetUpId) throws ServiceException;

    /**
     * Get total beers for a meetUp
     *
     * @param meetUpId long
     * @return {@link AmountBeerDto}
     */
    AmountBeerDto getAmountBeer(long meetUpId) throws ServiceException;


    /**
     * Get meetUp By TemperatureDay
     *
     * @param meetUpId long
     * @return {@link WeatherMeetUpDto}
     */
    WeatherMeetUpDto getTemperatureDay(long meetUpId) throws ServiceException;

    /**
     * Create a new MeetUp
     *
     * @param meetUpDto {@link MeetUpDto}
     * @return {@link MeetUpDto}
     */
    MeetUpDto createMeetUp(MeetUpDto meetUpDto) throws ServiceException;
}
