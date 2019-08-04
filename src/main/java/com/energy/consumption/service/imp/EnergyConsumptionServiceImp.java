package com.energy.consumption.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	private CounterRepository repository;

	@Autowired
	private CounterDetailRepository counterDetailRepository;
	
	@Autowired
	private CounterDetailClient counterDetailClient;

	@Override
	public Long counterCallback(CounterForm form) throws BusinessException {
		CounterDetail counterDetail = getLocationById(form.getId());
		return repository.save(new Counter(counterDetail, form.getAmmount())).getId();
	}

	@Override
	public CounterList getCounterList() {
		return CounterList.converter(repository.findAll());
	}
	
	@Override
	public CounterDto getCounterById(Long id) throws BusinessException {
		Optional<Counter> counter = repository.findById(id);
		if(counter.isPresent()) {
			return new CounterDto(counter.get());
		}
		throw new BusinessException("Counter does not exists");
	}
	
	private CounterDetail getLocationById(Long locationId) {
		Optional<CounterDetail> counterDetail = counterDetailRepository.findById(locationId);
		if (counterDetail.isPresent()) {
			return counterDetail.get();
		}else {
			CounterDetail counterDetailService = counterDetailClient.getCounterDetail(locationId);
			return counterDetailRepository.save(counterDetailService);
		}
	}
}
