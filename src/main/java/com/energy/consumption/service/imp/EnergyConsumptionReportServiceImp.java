package com.energy.consumption.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.energy.consumption.exception.BusinessException;
import com.energy.consumption.model.Counter;
import com.energy.consumption.model.EnergyConsumption;
import com.energy.consumption.model.EnergyConsumptionList;
import com.energy.consumption.repository.CounterRepository;
import com.energy.consumption.service.EnergyConsumptionReportService;

/**
 * This class make the calculation of the energy consumption per village
 *
 * @author Wanessa Nascimento
 */
@Service
public class EnergyConsumptionReportServiceImp implements EnergyConsumptionReportService {
	
	@Autowired
	private CounterRepository repository;

	@Override
	public EnergyConsumptionList getEnergyConsumptioReport(int duration) throws BusinessException {
		List<Counter> counters = repository.findByData(calculateData(duration));
		if(counters != null) {
			return new EnergyConsumptionList(calculateConsumption(counters));
		}
		throw new BusinessException("Does not have information for this range.");
	}
	
	private List<EnergyConsumption> calculateConsumption(List<Counter> counters) {
		Map<String, Double> map = new HashMap<>();
		for(Counter c : counters) {
			String key = c.getVillageName();
			if(!map.containsKey(key)) {
				map.put(c.getVillageName(), c.getAmmount());
			} else {
				map.replace(key, map.get(key) + c.getAmmount());
			}
		}
		return populateEnergyconsumptionList(map);
	}

	private List<EnergyConsumption> populateEnergyconsumptionList( Map<String, Double> map) {
		List<EnergyConsumption> energyConsumption = new ArrayList<>();
		for (Map.Entry<String,Double> value : map.entrySet())  {
			energyConsumption.add(new EnergyConsumption(value.getKey(), value.getValue()));
		}
		return energyConsumption;
	}

	private LocalDateTime calculateData(int duration) {
		return LocalDateTime.now().minusHours(duration);
	}
}
