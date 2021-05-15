package com.agreggio.challenge.birras.santander.login.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialsDto {

    @JsonProperty(value = "user_name")
    private String username;

    @JsonProperty(value = "password")
    private String password;


    @JsonProperty(value = "token")
    @ApiModelProperty(hidden = true)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
