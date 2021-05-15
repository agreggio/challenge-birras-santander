package com.agreggio.challenge.birras.santander.login.rest;

import com.agreggio.challenge.birras.santander.common.dto.ResponseDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.Collection;


@RestControllerAdvice(basePackages = "com.agreggio.challenge.birras.santander.login.controller")
public class StandardResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        return !("customException".equals(returnType.getMethod().getName()));

    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        ResponseDto standardResponse = new ResponseDto(new ArrayList<>(), new ArrayList<>());

        if (body instanceof Collection) {
            standardResponse.setData(body);
        } else {
            standardResponse.setData(new ArrayList<>());
            if (body != null) {
                ((ArrayList) standardResponse.getData()).add(body);
            }
        }


        return standardResponse;
    }
}