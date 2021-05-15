package com.agreggio.challenge.birras.santander.meet.up.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherMeetUpDto {

    @JsonProperty("meet_up")
    private MeetUpDto meetUpDto;

    @JsonProperty("temperature")
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public MeetUpDto getMeetUpDto() {
        return meetUpDto;
    }

    public void setMeetUpDto(MeetUpDto meetUpDto) {
        this.meetUpDto = meetUpDto;
    }
}
