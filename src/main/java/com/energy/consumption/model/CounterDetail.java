package com.energy.consumption.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
* 
* @author Wanessa Nascimento
*
*/
@Entity
public class CounterDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private String villages;
	
	public CounterDetail() {
	}

	public CounterDetail(Long id, String villages) {
		this.id = id;
		this.villages = villages;
	}

	public Long getId() {
		return id;
	}

	public String getVillages() {
		return villages;
	}
}
