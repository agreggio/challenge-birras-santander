package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import com.agreggio.challenge.birras.santander.common.entitie.Guest;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.common.repository.GuestRepository;
import com.agreggio.challenge.birras.santander.meet.up.dto.GuestDto;
import com.agreggio.challenge.birras.santander.meet.up.service.GuestService;
import com.agreggio.challenge.birras.santander.meet.up.service.MeetUpService;
import com.agreggio.challenge.birras.santander.meet.up.service.SecurityContextService;
import com.agreggio.challenge.birras.santander.meet.up.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.Optional;


@Service
public class GuestServiceImpl implements GuestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuestServiceImpl.class);

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    UserService userService;

    @Autowired
    MeetUpService meetUpService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SecurityContextService securityContextService;

    @Override
    public int getTotalGuests(long meetUpId) throws ServiceException {

        LOGGER.info("Get total guests by meetUpId: "+meetUpId);

        int totalGuests = guestRepository.countByMeetUpIdAndDeleteDateIsNull(meetUpId);

        if (totalGuests == 0){

            LOGGER.warn("Total guest 0 by meetUpId: "+ meetUpId);
            throw new ServiceException("No hay invitados registrados para la meetUp con id: "+ meetUpId);
        }

        return totalGuests;
    }


    @Override
    public GuestDto saveGuest(long meetUpId) throws ServiceException {

        Guest guest = new Guest();

        guest.setMeetUp(meetUpService.findMeetUp(meetUpId));

        String userName = securityContextService.getUserName();

        guest.setUser(userService.findUser(userName));

        LOGGER.info("Save guest by meetUpId: "+meetUpId+" and userName: "+userName);

        Guest newGuest = new Guest();

        try {
            newGuest = guestRepository.save(guest);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("userName :"+userName+" already registered in MeetUp: " + meetUpId);
            throw new ServiceException("El usuario: " +userName+ " ya esta registrado en la MeetUp: "+meetUpId);
        }


        return modelMapper.map(newGuest, GuestDto.class);

    }


    @Override
    public GuestDto updateGuest(long meetUpId) throws ServiceException {

        String userName = securityContextService.getUserName();

        Guest guest = findGuest(meetUpId, userService.findUser(userName).getId());

        guest.setCheckIn(true);

        LOGGER.info("Update guest by meetUpId: "+meetUpId+" and userName: "+userName);

        return modelMapper.map(guestRepository.save(guest), GuestDto.class);

    }


    @Override
    public Guest findGuest(long meetUpId, long userId) throws ServiceException {

        LOGGER.info("Find guest by meetUpId: "+meetUpId+" and userId: "+userId);

        Optional<Guest> guestsOptional = guestRepository.findByMeetUpIdAndUserIdAndDeleteDateIsNull(meetUpId, userId);

        /*
         * If optional is not present exit
         * */
        if (!guestsOptional.isPresent()){

            LOGGER.warn("Not found guest by meetUpId: "+meetUpId+" and userId: "+userId);
            throw new ServiceException("No se encontro invitado con id:" + userId + " para la meetUp con id: " + meetUpId);
        }

        return guestsOptional.get();
    }


}
