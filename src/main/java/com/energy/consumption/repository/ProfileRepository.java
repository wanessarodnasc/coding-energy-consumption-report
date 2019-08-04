package com.energy.consumption.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energy.consumption.model.Profile;

/**
* 
* @author Wanessa Nascimento
*
*/
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
