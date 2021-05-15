package com.agreggio.challenge.birras.santander.meet.up.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureDayDto {

	@JsonProperty("day")
	private Double day;

	@JsonProperty("min")
	private Double min;

	@JsonProperty("max")
	private Double max;

	@JsonProperty("night")
	private Double night;

	@JsonProperty("eve")
	private Double eve;

	@JsonProperty("morn")
	private Double morn;


	public Double getDay() {
		return day;
	}

	public void setDay(Double day) {
		this.day = day;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getNight() {
		return night;
	}

	public void setNight(Double night) {
		this.night = night;
	}

	public Double getEve() {
		return eve;
	}

	public void setEve(Double eve) {
		this.eve = eve;
	}

	public Double getMorn() {
		return morn;
	}

	public void setMorn(Double morn) {
		this.morn = morn;
	}
}
