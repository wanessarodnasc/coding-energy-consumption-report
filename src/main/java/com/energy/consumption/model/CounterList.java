package com.energy.consumption.model;

import java.util.ArrayList;
import java.util.List;

import com.energy.consumption.controller.dto.CounterDto;

public class CounterList {
	
	private List<CounterDto> counters;

	public CounterList() {
	}

	public CounterList(List<CounterDto> countersList) {
		this.counters = countersList;
	}

	public List<CounterDto> getCounters() {
		return counters;
	}

	public static CounterList converter(List<Counter> counters) {
		List<CounterDto> countersList = new ArrayList<>();
		for(Counter counter : counters) {
			countersList.add(new CounterDto(counter));
		}
		return new CounterList(countersList);
	}
}
