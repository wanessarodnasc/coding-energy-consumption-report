package com.energy.consumption.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class CounterForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Id is mandatory")
	private Long id;
	
	@NotBlank(message = "Ammount is mandatory")
	private Double ammount;

	public Long getId() {
		return id;
	}

	public Double getAmmount() {
		return ammount;
	}
}
