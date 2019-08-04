package com.energy.consumption.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.energy.consumption.model.CounterDetail;

/**
* 
* @author Wanessa Nascimento
*
*/
@RunWith(SpringRunner.class)
@DataJpaTest
public class LocationRepositoryTest {

	@Autowired
	private CounterDetailRepository repository;

	@Test
	public void saveUser() throws Exception {
		repository.save(new CounterDetail());
	}
	
	@Test
    public void findAll() {
        repository.save(new CounterDetail());
        assertNotNull(repository.findAll());
    }
	
	@Test
	public void testFindAllById() {
		CounterDetail user = repository.save(new CounterDetail());
		Optional<CounterDetail> userReturned = repository.findById(user.getId());
		assertEquals(true, userReturned.isPresent());
	}
	
	@Test
	public void testGetOne() {
		CounterDetail user = repository.save(new CounterDetail());
		assertEquals(user.getId(), repository.getOne(user.getId()).getId());
	}
}
