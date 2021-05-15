package com.agreggio.challenge.birras.santander.login.service.impl;

import com.agreggio.challenge.birras.santander.common.constant.JwtConstant;
import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.login.dto.CredentialsDto;
import com.agreggio.challenge.birras.santander.login.service.CredentialService;
import com.agreggio.challenge.birras.santander.login.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialServiceImpl implements CredentialService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public CredentialsDto getCredentialsDto(CredentialsDto credentialsDto) throws ServiceException {

        LOGGER.info("Get credentials");

        validateCredentialDto(credentialsDto);

        User user = userService.findUser(credentialsDto.getUsername(), credentialsDto.getPassword());

        String token = getJWTToken(credentialsDto.getUsername(),user.getUserType().getValue());
        credentialsDto.setUsername(credentialsDto.getUsername());
        credentialsDto.setToken(token);
        credentialsDto.setPassword(null);

    return credentialsDto;

    }

    private void validateCredentialDto(CredentialsDto credentialsDto) throws ServiceException {

        LOGGER.info("validate credentials");

        if (credentialsDto.getUsername() == null){
            LOGGER.warn("Username is null");
            throw new ServiceException("El usuario no puede ser nulo o vacio");
        }

        if (credentialsDto.getPassword() == null){
            LOGGER.warn("Password is null");
            throw new ServiceException("El password no puede ser nulo o vacio");
        }

    }

    private String getJWTToken(String username, String value) {

        LOGGER.info("Get JWT Token");

        String secretKey = JwtConstant.SECRET;
        List<GrantedAuthority> grantedAuthorities;

        if(value.equals("ADMIN")) {
            grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        }else {
            grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_USER");
        }


        String token = Jwts
                .builder()
                .setId("santanderJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return JwtConstant.PREFIX + token;
    }

}
