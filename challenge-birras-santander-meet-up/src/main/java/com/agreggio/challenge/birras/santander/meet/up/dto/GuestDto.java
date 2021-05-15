package com.agreggio.challenge.birras.santander.meet.up.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class GuestDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("meet_up")
    private MeetUpDto meetUp;

    @JsonProperty("user")
    private UserDto user;

    @JsonProperty("check_in")
    boolean checkIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeetUpDto getMeetUp() {
        return meetUp;
    }

    public void setMeetUp(MeetUpDto meetUp) {
        this.meetUp = meetUp;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isCheckIn() {
        return checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }
}
