package com.energy.consumption.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.energy.consumption.model.Counter;

/**
* 
* @author Wanessa Nascimento
*
*/
@RunWith(SpringRunner.class)
@DataJpaTest
public class CounterRepositoryTest {

	@Autowired
	private CounterRepository repository;

	@Test
	public void saveUser() throws Exception {
		repository.save(new Counter());
	}
	
	@Test
    public void findAll() {
        repository.save(new Counter());
        assertNotNull(repository.findAll());
    }
	
	@Test
	public void testFindAllById() {
		Counter user = repository.save(new Counter());
		Optional<Counter> userReturned = repository.findById(user.getId());
		assertEquals(true, userReturned.isPresent());
	}
	
	@Test
	public void testGetOne() {
		Counter user = repository.save(new Counter());
		assertEquals(user.getId(), repository.getOne(user.getId()).getId());
	}
}
