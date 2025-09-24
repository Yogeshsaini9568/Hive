package com.hive.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Date;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email address")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;
	
	@NotBlank(message = "Phone number is required")
	@Size(min = 10, max = 10, message = "Phone number must be 10 digits")
	private String phone;
	
	private Date dob;
	
	@NotBlank(message = "Gender is required")
	private String gender;
	
	private byte[] photo;

	private Address address;
}
