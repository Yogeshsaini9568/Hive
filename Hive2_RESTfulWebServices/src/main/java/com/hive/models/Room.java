package com.hive.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    @ManyToOne
    @ToString.Exclude
    @JsonBackReference
    private Property property;
    @OneToOne(cascade = CascadeType.ALL)
    private Amenity amenity;
    
}