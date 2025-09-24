package com.hive.models;



import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
	private String email;
	private String name;
	private String password;
	private String phone;
	private String gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private byte[] photo;
	private byte[] aadhaarCard;
	private Date registrationDate;

	private List<Property> properties;
	
	private Address address;

}
