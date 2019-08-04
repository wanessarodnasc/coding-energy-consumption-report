package com.energy.consumption.service.client;

import com.energy.consumption.model.CounterDetail;

/**
*
* This interface provide a contract to consult counter details service. Is possible
* implement to more than one counter supply.
*
* @author Wanessa Nascimento
* 
*/
public interface CounterDetailClient {
	
	CounterDetail getCounterDetail(Long id);

}