package com.energy.consumption.controller.dto;

import java.io.Serializable;

import com.energy.consumption.model.CounterDetail;

public class ContierDetailDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String villageName;
	
	public ContierDetailDto() {
	}

	public ContierDetailDto(CounterDetail counterDetail) {
		this.id = counterDetail.getId();
		this.villageName = counterDetail.getVillageName();
	}

	public Long getId() {
		return id;
	}

	public String getVillageName() {
		return villageName;
	}
}
