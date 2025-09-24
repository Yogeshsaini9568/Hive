package com.hive.models;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Owner {
	@Id
	private String email;
	private String name;
	private String password;
	private String phone;
	private String gender;
	@Temporal(TemporalType.DATE)
	private Date dob;
	private byte[] photo;
	private byte[] aadhaarCard;
	@Temporal(TemporalType.DATE)
	private Date registrationDate;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ToString.Exclude
	@JsonManagedReference 
	private List<Property> properties;
	
	@OneToOne
	private Address address;

}