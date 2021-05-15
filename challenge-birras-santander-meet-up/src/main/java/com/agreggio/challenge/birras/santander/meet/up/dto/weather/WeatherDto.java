package com.agreggio.challenge.birras.santander.meet.up.dto.weather;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;


public class WeatherDto {

	@JsonProperty("dt")
	private Date date;

	@JsonProperty("temp")
	private TemperatureDayDto temperatureDay;

	public Date getDate() {
		return date;
	}

	public void setDate(Long date) {

		this.date = DateUtils.addHours(new Date((date * 1000)),-13);

	}

	public TemperatureDayDto getTemperatureDay() {
		return temperatureDay;
	}

	public void setTemperatureDay(TemperatureDayDto temperatureDay) {
		this.temperatureDay = temperatureDay;
	}
}
