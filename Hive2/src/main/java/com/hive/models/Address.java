package com.hive.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {
	private int id;
	private String country;
	private String state;
	private String city;
	private String sector;
	private String houseNo;
	private String pincode;
}