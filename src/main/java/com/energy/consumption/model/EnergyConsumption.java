package com.energy.consumption.model;

import java.io.Serializable;

/**
* 
* @author Wanessa Nascimento
*
*/
public class EnergyConsumption implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String villages;
	
	private Double consumption;
	

	public EnergyConsumption() {
	}

	public EnergyConsumption(String villages, Double consumption) {
		this.villages = villages;
		this.consumption = consumption;
	}

	public String getVillages() {
		return villages;
	}

	public Double getConsumption() {
		return consumption;
	}
}
