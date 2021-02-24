package com.safemtech.process.automation.srumtomation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rb_user")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String userName;

	private String password;

	private String role;

}
