package com.energy.consumption.controller.dto;

import java.io.Serializable;

/**
* 
* @author Wanessa Nascimento
*
*/
public class TokenDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String token;

	public TokenDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}
