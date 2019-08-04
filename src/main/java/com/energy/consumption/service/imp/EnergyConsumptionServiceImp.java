package com.energy.consumption.service.imp;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.energy.consumption.controller.dto.CounterDto;
import com.energy.consumption.exception.BusinessException;
import com.energy.consumption.form.CounterForm;
import com.energy.consumption.model.Counter;
import com.energy.consumption.model.CounterDetail;
import com.energy.consumption.model.CounterList;
import com.energy.consumption.repository.CounterDetailRepository;
import com.energy.consumption.repository.CounterRepository;
import com.energy.consumption.service.EnergyConsumptionService;
import com.energy.consumption.service.client.CounterDetailClient;
/**
 * This class make the communication with the counter detail API
 *
 * @author Wanessa Nascimento
 */
@Service
public class EnergyConsumptionServiceImp implements EnergyConsumptionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EnergyConsumptionServiceImp.class);

	@Autowired
	private CounterRepository repository;

	@Autowired
	private CounterDetailRepository counterDetailRepository;
	
	@Autowired
	private CounterDetailClient counterDetailClient;

	@Override
	public Long counterCallback(CounterForm form) throws BusinessException {
		LOGGER.info("Call service counter callback");
		CounterDetail counterDetail = getLocationById(Long.valueOf(form.getId()));
		return repository.save(new Counter(counterDetail, Double.valueOf(form.getAmmount()))).getId();
	}

	@Override
	public CounterList getCounterList() {
		LOGGER.info("Call service counter list");
		return CounterList.converter(repository.findAll());
	}
	
	@Override
	public CounterDto getCounterById(Long id) throws BusinessException {
		LOGGER.info("Call service counter by id");
		Optional<Counter> counter = repository.findById(id);
		if(counter.isPresent()) {
			return new CounterDto(counter.get());
		}
		throw new BusinessException("Counter does not exists");
	}
	
	private CounterDetail getLocationById(Long locationId) throws BusinessException {
		Optional<CounterDetail> counterDetail = counterDetailRepository.findById(locationId);
		try {
			if (counterDetail.isPresent()) {
				return counterDetail.get();
			}else {
				CounterDetail counterDetailService = counterDetailClient.getCounterDetail(locationId);
				return counterDetailRepository.save(counterDetailService);
			}
		} catch (ResourceAccessException e) {
			LOGGER.error(e.getMessage());
			throw new BusinessException("Internal error");
		}
	}
}
