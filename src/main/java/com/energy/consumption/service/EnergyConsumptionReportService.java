package com.energy.consumption.service;

import com.energy.consumption.exception.BusinessException;
import com.energy.consumption.model.EnergyConsumptionList;

/**
 * This class make the communication with the counter detail API
 *
 * @author Wanessa Nascimento
 */
public interface EnergyConsumptionReportService {
	
	EnergyConsumptionList getEnergyConsumptioReport(int duration) throws BusinessException;

}
