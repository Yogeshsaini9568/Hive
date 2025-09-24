package com.hive.models;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Property {
	private int id;
    
	@NotBlank(message = "Property name is required")
	@Size(min = 2, max = 100, message = "Property name must be between 2 and 100 characters")
    private String name;
    
	@NotBlank(message = "Property description is required")
	@Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;
    
	@NotBlank(message = "Property type is required")
    private String type;            // Flats, Hostels, House, PGs, etc
    
    private byte[] img1;
    private byte[] img2;
    private byte[] img3;
    private byte[] img4;
    private byte[] electricityBill;
    private List<Room> rooms;
    
    private String ownerEmail;
}
