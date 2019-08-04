package com.energy.consumption.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.energy.consumption.model.Counter;

/**
* 
* @author Wanessa Nascimento
*
*/
public interface CounterRepository extends JpaRepository<Counter, Long> {

	@Query("from Counter c where c.currentDataTime > :calculateData")
	List<Counter> findByData(LocalDateTime calculateData);
	
}
