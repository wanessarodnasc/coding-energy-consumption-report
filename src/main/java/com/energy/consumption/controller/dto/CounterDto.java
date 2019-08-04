package com.energy.consumption.controller.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.energy.consumption.model.Counter;

public class CounterDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime currentDataTime;
	
	private String counterId;

	private String villageName;
	
	private Double ammount;

	public CounterDto(Counter counter) {
		this.currentDataTime = counter.getCurrentDataTime();
		this.counterId = counter.getCounterDetail().getId().toString();
		this.villageName = counter.getCounterDetail().getVillages();
		this.ammount = counter.getAmmount();
	}

	public LocalDateTime getCurrentDataTime() {
		return currentDataTime;
	}

	public String getCounterId() {
		return counterId;
	}

	public String getVillageName() {
		return villageName;
	}

	public Double getAmmount() {
		return ammount;
	}

}
