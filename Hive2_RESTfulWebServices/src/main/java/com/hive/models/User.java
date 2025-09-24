package com.hive.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class User {
	
	@Id
	private String email;
	private String password;
	private String name;
	private String phone;
	@Temporal(TemporalType.DATE)
	private Date dob;
	private String gender;
	@Column(nullable = true, columnDefinition = "longblob")
	private byte[] photo;

	@OneToOne
	private Address address;
}