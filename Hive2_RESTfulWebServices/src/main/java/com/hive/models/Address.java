package com.hive.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Address {
	@Id
	private int id;
	private String country;
	private String state;
	private String city;
	private String sector;
	private String houseNo;
	private String pincode;
}