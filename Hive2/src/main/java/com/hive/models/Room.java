package com.hive.models;


import java.sql.Date;

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
public class Room {
	private int roomID;
    private byte[] img1;
    private byte[] img2;
    private byte[] img3;
    private byte[] img4;
    private String sharingType; //single,double,triple etc
    private int price;
    private String available;
    private Date dateOfAvailability;
    private String description;
    private Property property;
    private Amenity amenity;
}
