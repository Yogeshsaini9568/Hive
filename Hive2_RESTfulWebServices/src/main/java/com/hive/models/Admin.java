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
public class Admin {
	@Id
	private String email;
	private String name;
	private String password;

}