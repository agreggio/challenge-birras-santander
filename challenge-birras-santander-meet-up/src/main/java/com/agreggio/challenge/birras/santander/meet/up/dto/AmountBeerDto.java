package com.agreggio.challenge.birras.santander.meet.up.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AmountBeerDto {

    @JsonProperty("temperature_day")
    private Double tempDay;

    @JsonProperty("amount_beer")
    private long amountBeer;

    public Double getTempDay() {
        return tempDay;
    }

    public void setTempDay(Double tempDay) {
        this.tempDay = tempDay;
    }

    public long getAmountBeer() {
        return amountBeer;
    }

    public void setAmountBeer(long amountBeer) {
        this.amountBeer = amountBeer;
    }
}
