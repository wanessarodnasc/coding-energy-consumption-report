package com.energy.consumption.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energy.consumption.model.CounterDetail;

/**
* 
* @author Wanessa Nascimento
*
*/
public interface CounterDetailRepository extends JpaRepository<CounterDetail, Long> {
	
}
