package com.energy.consumption.model;

import java.time.LocalDateTime;
import java.util.List;

import com.energy.consumption.util.CurrentDataTime;

public class EnergyConsumptionList {
	
	private LocalDateTime consultDateTime = CurrentDataTime.getCurrentTime();
	
	private List<EnergyConsumption> energyConsumption;
	
	public EnergyConsumptionList() {
	}

	public EnergyConsumptionList(List<EnergyConsumption> energyConsumptionList) {
		this.energyConsumption = energyConsumptionList;
	}

	public LocalDateTime getConsultDateTime() {
		return consultDateTime;
	}

	public List<EnergyConsumption> getEnergyConsumption() {
		return energyConsumption;
	}
}
