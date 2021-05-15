package com.agreggio.challenge.birras.santander.login.service;


import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.login.dto.CredentialsDto;


public interface CredentialService {

    /**
     * Get credentials
     *
     * @param credentialsDto {@link CredentialsDto}
     * @return {@link CredentialsDto}
     */
    CredentialsDto getCredentialsDto(CredentialsDto credentialsDto) throws ServiceException;

}
