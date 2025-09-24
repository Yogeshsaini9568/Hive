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
public class Amenity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean ac;
	private boolean wifi;
	private boolean washingMachine;
	private boolean powerBackup;
	private boolean Parking;
	private boolean lift;
	private boolean cctv;
	private boolean securityGuard;
	private boolean attachedBathroom;
	private boolean geyser;
	private boolean bed;
	private boolean studyTable;
	private boolean almira;
	private boolean foodIncluded; // Whether food/meals are included
	private boolean housekeeping; // Regular cleaning service
	private boolean drinkingWater; // RO / filtered water
	private boolean refrigerator;
	private boolean kitchenAccess;
	private boolean gymAccess;
	private boolean commonArea; // Lounge or TV room
}
