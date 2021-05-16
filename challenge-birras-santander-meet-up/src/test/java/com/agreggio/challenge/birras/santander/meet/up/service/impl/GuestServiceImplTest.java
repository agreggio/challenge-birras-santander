package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import com.agreggio.challenge.birras.santander.common.entitie.Guest;
import com.agreggio.challenge.birras.santander.common.entitie.MeetUp;
import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.common.repository.GuestRepository;
import com.agreggio.challenge.birras.santander.meet.up.dto.GuestDto;
import com.agreggio.challenge.birras.santander.meet.up.service.MeetUpService;
import com.agreggio.challenge.birras.santander.meet.up.service.SecurityContextService;
import com.agreggio.challenge.birras.santander.meet.up.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GuestServiceImplTest {

    @InjectMocks
    GuestServiceImpl guestService;

    @Mock
    GuestRepository guestRepositoryMock;

    @Mock
    UserService userServiceMock;

    @Mock
    MeetUpService meetUpServiceMock;

    @Mock
    ModelMapper modelMapperMock;

    @Mock
    SecurityContextService securityContextServiceMock;

    @Test
    public void getTotalGuestsTest_success() throws ServiceException {

        when(guestRepositoryMock.countByMeetUpIdAndDeleteDateIsNull(anyLong())).thenReturn(1);

        Assert.assertEquals(1,guestService.getTotalGuests(anyLong()));
    }

    @Test(expected = ServiceException.class)
    public void getTotalGuestsTest_fail() throws ServiceException {

        when(guestRepositoryMock.countByMeetUpIdAndDeleteDateIsNull(anyLong())).thenReturn(0);

        guestService.getTotalGuests(anyLong());
    }

    @Test
    public void saveGuest_success() throws ServiceException {

        when(meetUpServiceMock.findMeetUp((anyLong()))).thenReturn(new MeetUp());

        when(securityContextServiceMock.getUserName()).thenReturn("usuario");

        when(guestRepositoryMock.save(any(Guest.class))).thenReturn(new Guest());

        when(modelMapperMock.map(new Guest(),GuestDto.class)).thenReturn(new GuestDto());

        Assert.assertNull(guestService.saveGuest(anyLong()));
    }

    @Test(expected = ServiceException.class)
    public void saveGuest_fail() throws ServiceException {

        when(meetUpServiceMock.findMeetUp((anyLong()))).thenReturn(new MeetUp());

        when(securityContextServiceMock.getUserName()).thenReturn("usuario");

        when(guestRepositoryMock.save(any(Guest.class))).thenThrow(DataIntegrityViolationException.class);

        guestService.saveGuest(anyLong());
    }

    @Test
    public void updateGuest_success()  throws ServiceException {

        when(securityContextServiceMock.getUserName()).thenReturn("usuario");

        User user = new User();
        user.setId(1L);

        when(userServiceMock.findUser(anyString())).thenReturn(user);

        when(guestRepositoryMock.findByMeetUpIdAndUserIdAndDeleteDateIsNull(anyLong(), anyLong()))
                .thenReturn(Optional.of(new Guest()));

        guestService.updateGuest(1L);
    }

    @Test(expected = ServiceException.class)
    public void updateGuest_fail()  throws ServiceException {

        when(securityContextServiceMock.getUserName()).thenReturn("usuario");

        User user = new User();
        user.setId(1L);

        when(userServiceMock.findUser(anyString())).thenReturn(user);

        when(guestRepositoryMock.findByMeetUpIdAndUserIdAndDeleteDateIsNull(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        guestService.updateGuest(1L);
    }

    @Test
    public void findGuest_success() throws ServiceException {

        User user = new User();
        user.setId(1L);

        Guest guest = new Guest();
        guest.setUser(user);

        when(guestRepositoryMock.findByMeetUpIdAndUserIdAndDeleteDateIsNull(anyLong(), anyLong()))
                .thenReturn(Optional.of(guest));

        Assert.assertEquals(user.getId(),guestService.findGuest(1L,1L).getUser().getId());
    }

    @Test(expected = ServiceException.class)
    public void findGuest_fail() throws ServiceException {

        User user = new User();
        user.setId(1L);

        Guest guest = new Guest();
        guest.setUser(user);

        when(guestRepositoryMock.findByMeetUpIdAndUserIdAndDeleteDateIsNull(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        guestService.findGuest(1L,1L).getUser().getId();
    }

}