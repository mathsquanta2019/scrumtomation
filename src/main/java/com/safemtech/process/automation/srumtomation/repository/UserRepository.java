package com.safemtech.process.automation.srumtomation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safemtech.process.automation.srumtomation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);
}
