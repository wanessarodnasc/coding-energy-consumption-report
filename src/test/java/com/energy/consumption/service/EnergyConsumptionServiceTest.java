package com.energy.consumption.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.energy.consumption.exception.BusinessException;
import com.energy.consumption.model.Counter;
import com.energy.consumption.model.CounterDetail;
import com.energy.consumption.repository.CounterDetailRepository;
import com.energy.consumption.repository.CounterRepository;

/**
 * 
 * @author Wanessa Nascimento
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnergyConsumptionServiceTest {

	@Autowired(required=true)
	private CounterRepository repository;
	
	@Autowired(required=true)
	private CounterDetailRepository counterDetailRepository;
	
	@Test
	public void counterCallback() throws BusinessException {
		Counter counter = repository.save(new Counter(getCounterDetail("1"), 10000.000));
		assertEquals("Teste 1", counter.getVillageName());
	}
	
	@Test
	public void getCounterById() throws BusinessException {
		Counter counter = repository.save(new Counter(getCounterDetail("2"), 10000.000));
		Optional<Counter> counterSaved = repository.findById(counter.getId());
		assertEquals(true, counterSaved.isPresent());
	}
	
	@Test
	public void getCounterList() throws BusinessException {
		repository.save(new Counter(getCounterDetail("3"), 10000.000));
		repository.save(new Counter(getCounterDetail("4"), 20000.000));
		List<Counter> counters = repository.findAll();
		assertEquals(true, counters.size() >= 2);
	}
	
	private CounterDetail getCounterDetail(String value) {
		return counterDetailRepository.save(new CounterDetail(Long.valueOf(value), "Teste ".concat(value)));
	}
}
