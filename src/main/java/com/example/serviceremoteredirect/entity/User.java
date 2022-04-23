package com.example.serviceremoteredirect.entity;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Component
@Entity(name="user_service")
public class User {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	@Size(min=1)
	@Column(unique=true)
	private final String email;

	@Size(min = 5)
	private final String password;


	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}



	public User() {
		super();
		email = null;
		password = null;
	}
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}




}
