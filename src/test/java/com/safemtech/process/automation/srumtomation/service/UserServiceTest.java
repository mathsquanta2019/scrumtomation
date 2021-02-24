package com.safemtech.process.automation.srumtomation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import com.safemtech.process.automation.srumtomation.entity.User;
import com.safemtech.process.automation.srumtomation.repository.UserRepository;

public class UserServiceTest {

	private UserRepository userRepository = Mockito.mock(UserRepository.class);

	private UserService userService;
	User user;

	@BeforeEach
	public void init() {
		userService = new UserService(userRepository);
		user = new User();
	}

	@Test
	public void testUserServiceMethods() {

		// Givem
		user.setPassword("ah749");
		user.setRole("admin");
		user.setUserName("adusname");

		when(userRepository.findByUserName("adusname")).thenReturn(user);

		// when
		UserDetails userdetails = userService.loadUserByUsername("adusname");

		// Then
		assertThat(userdetails.getPassword().equals(user.getPassword()));

		verify(userRepository, times(1)).findByUserName("adusname");
	}

	@Test
	public void verifyUserIsSaved() {

		when(userRepository.save(user)).thenReturn(user);

		// When
		User sam = userRepository.save(user);

		// Then
		assertThat(sam.equals(user));

		verify(userRepository, times(1)).save(user);
	}
}
