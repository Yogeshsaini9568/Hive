package com.hive.models;

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
public class Listing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String availabilityStatus;
	private int buildingId;
	private int RoomId;

}