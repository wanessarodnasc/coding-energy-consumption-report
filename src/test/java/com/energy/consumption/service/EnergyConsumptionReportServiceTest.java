package com.energy.consumption.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.energy.consumption.exception.BusinessException;
import com.energy.consumption.model.Counter;
import com.energy.consumption.model.CounterDetail;
import com.energy.consumption.model.EnergyConsumption;
import com.energy.consumption.model.EnergyConsumptionList;
import com.energy.consumption.repository.CounterDetailRepository;
import com.energy.consumption.repository.CounterRepository;

/**
 * 
 * @author Wanessa Nascimento
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnergyConsumptionReportServiceTest {

	@Autowired(required=true)
	private EnergyConsumptionReportService service;
	
	@Autowired(required=true)
	private CounterRepository repository;
	
	@Autowired(required=true)
	private CounterDetailRepository counterDetailRepository;
	
	@Before
	public void beforeTest() {
		for(Integer i = 0; i <= 2 ; i ++) {
			Counter counter = new Counter(getCounterDetail(i.toString()), 10000.123); 
			repository.save(counter);
		}
		for(Integer i = 0; i <= 2 ; i ++) {
			Counter counter = new Counter(getCounterDetail(i.toString()), 20000.123); 
			repository.save(counter);
		}
		
		for(Integer i = 0; i <= 2 ; i ++) {
			Counter counter = new Counter(getCounterDetail(i.toString()), 5699.00, LocalDateTime.now().minusHours(25)); 
			repository.save(counter);
		}
	}

	private CounterDetail getCounterDetail(String village) {
		CounterDetail detail = new CounterDetail(Long.valueOf(village), "Teste ".concat(village));
		return counterDetailRepository.save(detail);
	}

	@Test
	public void getEnergyConsumptionReport() throws BusinessException {
		EnergyConsumptionList list = service.getEnergyConsumptioReport(24);
		for(EnergyConsumption energy : list.getEnergyConsumption()) {
			assertEquals(Double.valueOf(30000.246), energy.getConsumption());
		}
	}
}
