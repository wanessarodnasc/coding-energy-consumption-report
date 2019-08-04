package com.energy.consumption.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.energy.consumption.util.CurrentDataTime;

/**
* 
* @author Wanessa Nascimento
*
*/
@Entity
public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDateTime currentDataTime;
	
	@ManyToOne
	private CounterDetail counterDetail;
	
	private Double ammount;
	
	public Counter() {
	}

	public Counter(CounterDetail counterDetail, Double ammount) {
		this.currentDataTime = CurrentDataTime.getCurrentTime();
		this.counterDetail = counterDetail;
		this.ammount = ammount;
	}

	public Counter(CounterDetail counterDetail, Double ammount, LocalDateTime minusHours) {
		this.currentDataTime = minusHours;
		this.counterDetail = counterDetail;
		this.ammount = ammount;
	}

	public void setCurrentDataTime(LocalDateTime currentDataTime) {
		this.currentDataTime = currentDataTime;
	}

	public void setLocation(CounterDetail counterDetail) {
		this.counterDetail = counterDetail;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCurrentDataTime() {
		return currentDataTime;
	}

	public CounterDetail getCounterDetail() {
		return counterDetail;
	}

	public Double getAmmount() {
		return ammount;
	}
	
	public String getVillageName() {
		return counterDetail.getVillageName();
	}
	
}
