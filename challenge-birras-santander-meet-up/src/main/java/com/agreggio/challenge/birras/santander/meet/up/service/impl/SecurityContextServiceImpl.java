package com.agreggio.challenge.birras.santander.meet.up.service.impl;

import com.agreggio.challenge.birras.santander.meet.up.service.SecurityContextService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextServiceImpl implements SecurityContextService {

    @Override
    public String getUserName(){

        return SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString();

    }


}
