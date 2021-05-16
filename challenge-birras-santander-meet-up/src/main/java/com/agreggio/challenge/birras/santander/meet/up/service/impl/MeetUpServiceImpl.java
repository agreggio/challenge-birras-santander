package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import com.agreggio.challenge.birras.santander.common.entitie.MeetUp;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.common.repository.MeetUpRepository;
import com.agreggio.challenge.birras.santander.meet.up.dto.AmountBeerDto;
import com.agreggio.challenge.birras.santander.meet.up.dto.MeetUpDto;
import com.agreggio.challenge.birras.santander.meet.up.dto.WeatherMeetUpDto;
import com.agreggio.challenge.birras.santander.meet.up.manager.WeatherCacheManager;
import com.agreggio.challenge.birras.santander.meet.up.service.GuestService;
import com.agreggio.challenge.birras.santander.meet.up.service.MeetUpService;
import com.agreggio.challenge.birras.santander.meet.up.service.MessageMqService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;



@Service
public class MeetUpServiceImpl implements MeetUpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetUpServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MeetUpRepository meetUpRepository;

    @Autowired
    private WeatherCacheManager weatherCacheManager;

    @Autowired
    private GuestService guestService;

    @Autowired
    private MessageMqService messageMqService;


    @Override
    public AmountBeerDto getAmountBeer(long meetUpId) throws ServiceException {

        LOGGER.info("Call getAmountBeer by meetUpId: "+meetUpId);

        /*
        * Find and validate if meetUp exist
        * */
        findMeetUp(meetUpId);

        int totalGuests = guestService.getTotalGuests(meetUpId);

        AmountBeerDto amountBeerDto = new AmountBeerDto();

        amountBeerDto.setTempDay(getTemperatureDay(meetUpId).getTemperature());

        /*
         * if temp > 20 C°----- 0.75
         * */
        if (amountBeerDto.getTempDay() < 20){
            amountBeerDto.setAmountBeer(calculateAmountBeer(totalGuests,0.75));
        }

        /*
         * if temp >= 20 && temp <= 24 C°----- 1
         * */
        if (amountBeerDto.getTempDay() >= 20 && amountBeerDto.getTempDay() <= 24){
            amountBeerDto.setAmountBeer(calculateAmountBeer(totalGuests,1.0));
        }

        /*
        * if temp > 24 C°----- 2
        * */
        if (amountBeerDto.getTempDay() > 24){
            amountBeerDto.setAmountBeer(calculateAmountBeer(totalGuests,2.0));
        }


        return amountBeerDto;
    }


    @Override
    public WeatherMeetUpDto getTemperatureDay(long meetUpId) throws ServiceException {

        LOGGER.info("Get temperature day by meetUpId: " + meetUpId);

        MeetUp meetUp = findMeetUp(meetUpId);

        WeatherMeetUpDto weatherMeetUpDto = new WeatherMeetUpDto();

        weatherMeetUpDto.setMeetUpDto(modelMapper.map(meetUp, MeetUpDto.class));

        /*
         * Find temperature day on cache
         * */
        weatherMeetUpDto.setTemperature(weatherCacheManager.getTemperatureByDate(meetUp.getEventDate()));

        return weatherMeetUpDto;
    }


    @Override
    public MeetUpDto createMeetUp(MeetUpDto meetUpDto) throws ServiceException {

        if (meetUpDto.getEventDate().before(new Date())){
            LOGGER.warn("Error create meetUp with date: "+ meetUpDto.getEventDate());
            throw new ServiceException("No se puede crear meetUp con fecha: " +
                    new SimpleDateFormat("dd-MM-yyyy").format(meetUpDto.getEventDate()));
        }
        /*
        * Convert meetUpDto to entity MeetUp
        * */
        MeetUp meetUp = modelMapper.map(meetUpDto,MeetUp.class);

        MeetUp newMeetUp;

        /*
         * Save MeetUp in data base
         * */
        try {
            newMeetUp = meetUpRepository.save(meetUp);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("MeetUp already exist");
            throw new ServiceException("Ya existe una MeetUp con fecha: "  +
                    new SimpleDateFormat("dd-MM-yyyy").format(meetUp.getEventDate()));
        }

        /*
        * Send message AMQ
        * */
        messageMqService.send(newMeetUp);

        LOGGER.info("Save a new MeetUp");

        return modelMapper.map(newMeetUp,MeetUpDto.class);

    }


    @Override
    public MeetUp findMeetUp(long meetUpId) throws ServiceException {

        LOGGER.info("Find meetUpId: "+ meetUpId);

        /*
         * Find on data base
         * */
        Optional<MeetUp> optionalMeetUp = meetUpRepository.findMeetUpByIdAndDeleteDateIsNull(meetUpId);

        /*
         * If optional is not present exit
         * */
        if (!optionalMeetUp.isPresent()){
            LOGGER.warn("Not found meetUpId: "+ meetUpId);
            throw new ServiceException("No se encontro MeetUp con id: " + meetUpId);
        }

        return optionalMeetUp.get();
    }


    private long calculateAmountBeer(int totalGuests, double beerAmountByGuest){

        LOGGER.info("Calculate amount beer by totalGuests: " + totalGuests + " and beerAmountByGuest: " + beerAmountByGuest);

        return (long) ((Math.ceil((beerAmountByGuest * totalGuests)/6)) * 6);
    }

}
