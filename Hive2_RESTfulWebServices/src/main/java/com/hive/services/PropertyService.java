package com.hive.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.models.Owner;
import com.hive.models.Property;
import com.hive.models.Room;
import com.hive.repo.OwnerRepoCustom;
import com.hive.repo.PropertyCustomRepo;
import com.hive.repo.PropertyRepo;
import com.hive.repo.RoomRepo;

@Service
public class PropertyService {

	@Autowired
	private PropertyCustomRepo customRepo;
	
	@Autowired
	PropertyRepo propertyRepo;
	
	@Autowired
	RoomRepo roomRepo; 
	
	@Autowired
	OwnerRepoCustom ownerRepo;
	
	public Owner addProperty(Property property) {
		propertyRepo.save(property);
		return ownerRepo.getOwner(property.getOwner().getEmail());
	}

	public Owner addRoom(Room room) {
		roomRepo.save(room);
		Property property=propertyRepo.findById(room.getProperty().getId()).orElseThrow();
		return ownerRepo.getOwner(property.getOwner().getEmail());
	}

	public boolean uploadImages(Property property) {
		customRepo.uploadImages(property);
		return true;
	}

	public Property getProperty(int id) {
		return propertyRepo.findById(id).orElseThrow();
	}

	public List<Property> getProperties(String email) {
		return propertyRepo.findByOwnerEmail( email);
	}

	public List<Room> viewRooms(int id) {
		return roomRepo.findByPropertyId(id);
	}

	public List<Property> getPropertiesByName(String name) {
		return propertyRepo.findByNameContaining(name);
	}

//	public Owner getOwner(String email) {
//		return buildingRepo.getOwner(email);
//	}
//	
//	public Owner updateOwner(Owner owner) {
//		return buildingRepo.updateOwner(owner);
//	}
//
//	public boolean updatePhoto(String email, byte[] photo) {
//		return buildingRepo.updatePhoto(email, photo);
//	}
//
//	public byte[] getPhoto(String email) {
//		return buildingRepo.getPhoto(email);
//	}
//
//	public boolean updatePassword(String ownerEmail, String newPassword) {
//		return buildingRepo.updatePassword(ownerEmail,newPassword);
//	}
//
//	public Building updateBuilding(Building building) {
//		return buildingRepo.updateBuilding(building);
//	}

	

}
