package com.energy.consumption.service;

import com.energy.consumption.controller.dto.CounterDto;
import com.energy.consumption.exception.BusinessException;
import com.energy.consumption.form.CounterForm;
import com.energy.consumption.model.CounterList;

/**
* 
* @author Wanessa Nascimento
*
*/
public interface EnergyConsumptionService {
	
	Long counterCallback(CounterForm counter) throws BusinessException;
	
	CounterDto getCounterById(Long id) throws BusinessException;

	CounterList getCounterList() throws BusinessException;

}
