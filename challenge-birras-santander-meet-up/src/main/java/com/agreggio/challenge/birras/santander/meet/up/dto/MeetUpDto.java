package com.agreggio.challenge.birras.santander.meet.up.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;


public class MeetUpDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("direction")
    @NotNull(message = "event_date no puede ser null o vacio")
    private String direction;

    @JsonProperty("event_date")
    @NotNull(message = "event_date no puede ser null o vacio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "es-AR", timezone = "America/Argentina/Mendoza")
    private Date eventDate;

    @JsonProperty("amount_beer")
    @NotNull(message = "amount_beer no puede ser null o vacio")
    private Long amountBeer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Long getAmountBeer() {
        return amountBeer;
    }

    public void setAmountBeer(Long amountBeer) {
        this.amountBeer = amountBeer;
    }
}
