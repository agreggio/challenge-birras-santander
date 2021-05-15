package com.agreggio.challenge.birras.santander.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDto {

    @JsonProperty(value = "data")
    Object data;

    @JsonProperty(value = "error")
    Object error;

    public ResponseDto(Object data, Object error) {
        this.data = data;
        this.error = error;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
