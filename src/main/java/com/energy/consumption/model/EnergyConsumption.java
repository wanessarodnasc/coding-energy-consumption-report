package com.energy.consumption.model;

import java.io.Serializable;

/**
* 
* @author Wanessa Nascimento
*
*/
public class EnergyConsumption implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String villageName;
	
	private Double consumption;
	

	public EnergyConsumption() {
	}

	public EnergyConsumption(String villageName, Double consumption) {
		this.villageName = villageName;
		this.consumption = consumption;
	}

	public String getVillageName() {
		return villageName;
	}

	public Double getConsumption() {
		return consumption;
	}
}
