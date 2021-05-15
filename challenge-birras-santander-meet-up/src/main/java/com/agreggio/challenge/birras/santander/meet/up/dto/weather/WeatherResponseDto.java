package com.agreggio.challenge.birras.santander.meet.up.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherResponseDto {

		@JsonProperty("list")
		private List<WeatherDto> weatherDtoList;

	public List<WeatherDto> getWeatherDtoList() {
		return weatherDtoList;
	}

	public void setWeatherDtoList(List<WeatherDto> weatherDtoList) {
		this.weatherDtoList = weatherDtoList;
	}
}
