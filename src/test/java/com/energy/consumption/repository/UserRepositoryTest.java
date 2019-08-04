package com.energy.consumption.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.energy.consumption.model.User;
import com.energy.consumption.repository.UserRepository;

/**
* 
* @author Wanessa Nascimento
*
*/
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void saveUser() throws Exception {
		repository.save(new User());
	}
	
	@Test
    public void findAll() {
        repository.save(new User());
        assertNotNull(repository.findAll());
    }
	
	@Test
	public void testFindAllById() {
		User user = repository.save(new User());
		Optional<User> userReturned = repository.findById(user.getId());
		assertEquals(true, userReturned.isPresent());
	}
	
	@Test
	public void testGetOne() {
		User user = repository.save(new User());
		assertEquals(user.getId(), repository.getOne(user.getId()).getId());
	}
}
