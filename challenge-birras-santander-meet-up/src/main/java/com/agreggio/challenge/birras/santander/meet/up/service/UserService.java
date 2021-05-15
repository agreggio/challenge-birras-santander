package com.agreggio.challenge.birras.santander.meet.up.service;


import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;

public interface UserService {

    /**
     * Find User by id
     *
     * @param userId long
     * @return {@link User}
     */
    User findUser(long userId) throws ServiceException;


    /**
     * Find User by userName
     *
     * @param userName {@link String}
     * @return {@link User}
     */
    User findUser(String userName) throws ServiceException;

}
