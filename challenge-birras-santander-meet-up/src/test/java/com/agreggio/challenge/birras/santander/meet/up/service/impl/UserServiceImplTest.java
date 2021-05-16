package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import com.agreggio.challenge.birras.santander.common.entitie.Guest;
import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.common.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepositoryMock;


    @Test
    public void findUserByUserId_success() throws ServiceException {

        User user = new User();
        user.setId(1L);

        when(userRepositoryMock.findUserByIdAndDeleteDateIsNull(anyLong()))
                .thenReturn(Optional.of(user));

        long userId = userService.findUser(1L).getId();

        Assert.assertEquals(1L,userId);
    }

    @Test(expected = ServiceException.class)
    public void findUserByUserId_fail() throws ServiceException {

        when(userRepositoryMock.findUserByIdAndDeleteDateIsNull(anyLong()))
                .thenReturn(Optional.empty());

        userService.findUser(1L);
    }

    @Test
    public void findUserByUserName_success() throws ServiceException {

        User user = new User();
        user.setId(1L);

        when(userRepositoryMock.findUserByUserNameAndDeleteDateIsNull(anyString()))
                .thenReturn(Optional.of(user));

        long userId = userService.findUser("userName").getId();

        Assert.assertEquals(1L,userId);
    }

    @Test(expected = ServiceException.class)
    public void findUserByUserName_fail() throws ServiceException {

        when(userRepositoryMock.findUserByUserNameAndDeleteDateIsNull(anyString()))
                .thenReturn(Optional.empty());

        userService.findUser("userName");
    }
}