package com.energy.consumption.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energy.consumption.model.User;

/**
* 
* @author Wanessa Nascimento
*
*/
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
