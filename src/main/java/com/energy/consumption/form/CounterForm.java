package com.energy.consumption.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class CounterForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Id is mandatory")
	private String id;
	
	@NotBlank(message = "Ammount is mandatory")
	private String ammount;

	public String getId() {
		return id;
	}

	public String getAmmount() {
		return ammount;
	}
}
