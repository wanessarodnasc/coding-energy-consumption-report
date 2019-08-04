package com.energy.consumption.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.energy.consumption.controller.dto.CounterDto;
import com.energy.consumption.exception.BusinessException;
import com.energy.consumption.form.CounterForm;
import com.energy.consumption.model.CounterList;
import com.energy.consumption.model.EnergyConsumptionList;
import com.energy.consumption.service.EnergyConsumptionReportService;
import com.energy.consumption.service.EnergyConsumptionService;

/**
* 
* @author Wanessa Nascimento
*
*/
@RestController
public class EnergyConsumptionController {
	
	@Autowired
	private EnergyConsumptionService service;
	
	@Autowired
	private EnergyConsumptionReportService serviceReport;
	
	/**
	 * Insert data about energy consumption.
	 *
	 * @param CounterForm is a required parameter
	 * @return A String with the id of the register created
	 * @throws BusinessException 
	 * 
	 */
	@PostMapping("/counter-callback")
	@Transactional
	public ResponseEntity<String> authenticate(@RequestBody @Valid CounterForm form, UriComponentsBuilder uriBuilder) throws BusinessException {
		Long id = service.counterCallback(form);
		URI uri = uriBuilder.buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(id.toString());
	}
	
	/**
	 * Get Counter by id.
	 *
	 * @param Long id is a required parameter
	 * @return A CounterDto with a counter inserted information
	 * @throws BusinessException 
	 * 
	 */
	@GetMapping("/counter/{id}")
	public ResponseEntity<CounterDto> getCounterById(@PathVariable Long id) throws BusinessException {
		CounterDto counter = service.getCounterById(id);
		return ResponseEntity.ok(counter);
	}
	
	/**
	 * Get a list of Counters.
	 *
	 * @return A CounterList with a list of counters 
	 * @throws BusinessException 
	 * 
	 */
	@GetMapping("/counters")
	public ResponseEntity<CounterList> getCounterList() throws BusinessException {
		CounterList counters = service.getCounterList();
		return ResponseEntity.ok(counters);
	}
	
	/**
	 * Get energy consumption report of all villages.
	 *
	 * @param int duration in hours is a required parameter
	 * @return A EnergyConsumptionList with a list of energy consumption by duration 
	 * @throws BusinessException 
	 * 
	 */
	@GetMapping("/consumption-report")
	public ResponseEntity<EnergyConsumptionList> getEnergyConsumptioReport(@RequestParam int duration) throws BusinessException {
		EnergyConsumptionList list = serviceReport.getEnergyConsumptioReport(duration);
		return ResponseEntity.ok(list);
	}
}
