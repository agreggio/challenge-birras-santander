package com.agreggio.challenge.birras.santander.login.service;

import com.agreggio.challenge.birras.santander.common.entitie.UserType;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;

public interface UserTypeService {

    UserType findUserType(Long userTypeId) throws ServiceException;

    UserType newUserType(UserType userType);


}
