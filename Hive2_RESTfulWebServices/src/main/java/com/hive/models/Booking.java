package com.hive.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private int buildingId;
	private Date checkinDate;
	private String status;
	private Date bookingDate;
	private int numberOfMOnths;
	private Date checkoutDate;
	private int amount;
	private int transitionID;
	private String paymentMode;

}