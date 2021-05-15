package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.common.repository.UserRepository;
import com.agreggio.challenge.birras.santander.meet.up.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    public User findUser(long userId) throws ServiceException {

        LOGGER.info("Find User by userId: "+userId);

        /*
         * Find on data base
         * */
        Optional<User> optionalUser = userRepository.findUserByIdAndDeleteDateIsNull(userId);

        /*
         * If optional is not present exit
         * */
        if (!optionalUser.isPresent()){
            LOGGER.warn("Not found User by userId: "+userId);
            throw new ServiceException("No se encontro user con id: " + userId);
        }

        return optionalUser.get();
    }

    public User findUser(String userName) throws ServiceException {

        LOGGER.info("Find User by userId: "+userName);

        /*
         * Find on data base
         * */
        Optional<User> optionalUser = userRepository.findUserByUserNameAndDeleteDateIsNull(userName);

        /*
         * If optional is not present exit
         * */
        if (!optionalUser.isPresent()){
            LOGGER.warn("Not found User by userName: "+userName);
            throw new ServiceException("No se encontro user con userName: " + userName);
        }

        return optionalUser.get();
    }

}
