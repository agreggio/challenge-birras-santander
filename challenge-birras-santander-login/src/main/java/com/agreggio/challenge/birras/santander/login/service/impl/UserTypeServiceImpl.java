package com.agreggio.challenge.birras.santander.login.service.impl;

import com.agreggio.challenge.birras.santander.common.Util.EncodeUtil;
import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.entitie.UserType;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.common.repository.UserTypeRepository;
import com.agreggio.challenge.birras.santander.login.service.UserTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTypeServiceImpl.class);

    @Autowired
    UserTypeRepository userTypeRepository;


    @Override
    public UserType findUserType(Long userTypeId) throws ServiceException {

        Optional<UserType> optionalUserType =  userTypeRepository.findUserTypeByIdAndDeleteDateIsNull(userTypeId);

        LOGGER.info("Find UserType by userTypeId: "+ userTypeId);

        if(!optionalUserType.isPresent()) {

            LOGGER.warn("Not found UserType by userTypeId: "+ userTypeId);
            throw new ServiceException("No se encontro UserType: " + userTypeId );
        }

        return optionalUserType.get();

    }

    @Override
    public UserType newUserType(UserType userType) {

        LOGGER.warn("Save UserType by type: "+ userType.getValue());
        return userTypeRepository.save(userType);
    }


}
