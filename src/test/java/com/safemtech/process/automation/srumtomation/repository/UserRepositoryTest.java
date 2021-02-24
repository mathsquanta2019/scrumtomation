package com.safemtech.process.automation.srumtomation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.safemtech.process.automation.srumtomation.entity.User;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void userRepositoryTest() {
		
		//Given
		User user = new User();
		user.setPassword("pword");
		user.setUserName("sam");
		user.setRole("developer");
		
		testEntityManager.persistAndFlush(user);
		
		//when
		
		User sam = userRepository.findByUserName("sam");
		
		//Then
		
		assertThat(sam.getId().equals(user.getId()));
		
		
	}
	
}
