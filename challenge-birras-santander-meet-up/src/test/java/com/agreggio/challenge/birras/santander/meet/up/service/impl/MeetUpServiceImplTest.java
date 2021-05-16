package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import com.agreggio.challenge.birras.santander.common.entitie.MeetUp;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.common.repository.MeetUpRepository;
import com.agreggio.challenge.birras.santander.meet.up.dto.MeetUpDto;
import com.agreggio.challenge.birras.santander.meet.up.manager.WeatherCacheManager;
import com.agreggio.challenge.birras.santander.meet.up.service.GuestService;
import com.agreggio.challenge.birras.santander.meet.up.service.MessageMqService;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MeetUpServiceImplTest {

    @InjectMocks
    MeetUpServiceImpl meetUpService;

    @Mock
    ModelMapper modelMapperMock;

    @Mock
    MeetUpRepository meetUpRepositoryMock;

    @Mock
    WeatherCacheManager weatherCacheManagerMock;

    @Mock
    GuestService guestServiceMock;

    @Mock
    MessageMqService messageMqServiceMock;

    @Test
    public void getAmountBeer_success() throws ServiceException {

        /*
        * 1 Guest return 6 Beer
        *
        * */
        when(guestServiceMock.getTotalGuests(anyLong())).thenReturn(10);

        when(meetUpRepositoryMock.findMeetUpByIdAndDeleteDateIsNull(anyLong()))
                .thenReturn(Optional.of(new MeetUp()));

        MeetUp meetUp = new MeetUp();
        meetUp.setId(1L);

        MeetUpDto meetUpDto = new MeetUpDto();
        meetUpDto.setId(1L);


        Assert.assertEquals(12,meetUpService.getAmountBeer(1L).getAmountBeer());
    }


    @Test
    public void getTemperatureDay() throws ServiceException {

        when(meetUpRepositoryMock.findMeetUpByIdAndDeleteDateIsNull(anyLong()))
                .thenReturn(Optional.of(new MeetUp()));

        MeetUp meetUp = new MeetUp();
        meetUp.setId(1L);

        MeetUpDto meetUpDto = new MeetUpDto();
        meetUpDto.setId(1L);


        Assert.assertNotNull(meetUpService.getTemperatureDay(1L));
    }

    @Test
    public void createMeetUp_success() throws ServiceException {

        MeetUpDto meetUpDto = new MeetUpDto();
        meetUpDto.setId(1L);
        meetUpDto.setEventDate(DateUtils.addHours(new Date(),24));

        MeetUp meetUp = new MeetUp();
        meetUp.setId(1L);

        when(modelMapperMock.map(meetUpDto,MeetUp.class)).thenReturn(meetUp);

        when(meetUpRepositoryMock.save(any(MeetUp.class))).thenReturn(meetUp);

        when(modelMapperMock.map(meetUp,MeetUpDto.class)).thenReturn(meetUpDto);

        Assert.assertNotNull(meetUpService.createMeetUp(meetUpDto));
    }

    @Test(expected = ServiceException.class)
    public void createMeetUp_fail() throws ServiceException {

        MeetUpDto meetUpDto = new MeetUpDto();
        meetUpDto.setEventDate(DateUtils.addHours(new Date(),-24));

        meetUpService.createMeetUp(meetUpDto);
    }


}