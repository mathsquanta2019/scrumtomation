package com.safemtech.process.automation.srumtomation.service;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.safemtech.process.automation.srumtomation.entity.User;
import com.safemtech.process.automation.srumtomation.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("Querying database for user with {userName:{}}", username);

		User user = userRepository.findByUserName(username);

		if (user == null) {
			log.error("User not found");
			throw new UsernameNotFoundException(username.concat(" not found"));
		}

		Collection<SimpleGrantedAuthority> authorizedUsers = new HashSet<>();
		authorizedUsers.add(new SimpleGrantedAuthority(user.getRole()));

		log.info("Authorizing user:{}", username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				authorizedUsers);
	}

	public User createUser(User user) {

		log.info("Creating user:{} with role:{}", user.getUserName(), user.getRole());
		return userRepository.save(user);
	}

}
