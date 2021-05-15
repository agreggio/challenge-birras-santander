package com.agreggio.challenge.birras.santander.common.configuration;

import com.agreggio.challenge.birras.santander.common.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {

        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.getWriter().write(objectMapper.writeValueAsString(new ResponseDto(new ArrayList<>(),"Acceso Denegado")));

    }
}