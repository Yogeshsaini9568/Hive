package com.hive.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hive.models.Owner;
import com.hive.models.Property;
import com.hive.models.Room;
import com.hive.services.PropertyService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;
	
	@PostMapping("/addProperty")
	public Owner addProperty(@RequestBody Property property) {
		return propertyService.addProperty(property);
	}
	
	@GetMapping("/getProperty/{id}")
	public Property getProperty(@PathVariable int id) {
		return propertyService.getProperty(id);
	}
	
	@GetMapping("/getProperties/{email}")
	public List<Property> getProperties(@PathVariable String email) {
		return propertyService.getProperties(email);
	}
	
	@PostMapping("/addRoom")
	public Owner addRoom(@RequestBody Room room) {
		return propertyService.addRoom(room);
	}
	
	@GetMapping("/viewRooms/{id}")
	public List<Room> viewRooms(@PathVariable int id) {
		return propertyService.viewRooms(id);
	}
	
	@PostMapping("/uploadImages")
	public boolean uploadImages(@RequestBody Property property) {
		return propertyService.uploadImages(property);
	}
	
	@GetMapping("/getImage/{id}")
	public List<byte[]> getImage(@PathVariable int id, HttpServletResponse response) throws IOException {
		Property property=propertyService.getProperty(id);
		List<byte[]> images=new ArrayList<>();
		images.add(property.getImg1());
		images.add(property.getImg2());
		images.add(property.getImg3());
		images.add(property.getImg4());
		return images;
	}
	
	@GetMapping("/searchProperty/{name}")
	public List<Property> searchProperty(@PathVariable String name) {
		return propertyService.getPropertiesByName(name);
	}
	
//	@PutMapping("/updateBuilding")
//	public Building updateBuilding(@RequestBody Building building) {
//		return buildingService.updateBuilding(building);
//	}
//	
//
//	@GetMapping("/getBuildingPhoto/{email}")
//	public byte[] getBuildingPhoto(@PathVariable String email) {
//		return buildingService.getBuildingPhoto(email);
//	}
//
//	@PutMapping("/updateBuildingPhoto/{email}")
//	public boolean updateBuildingPhoto(@PathVariable String email, @RequestBody byte[] photo) {
//		return buildingService.updateBuildingPhoto(email, photo);
//	}

}
