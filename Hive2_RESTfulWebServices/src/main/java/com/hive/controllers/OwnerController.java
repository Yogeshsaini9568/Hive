package com.hive.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hive.models.Booking;
import com.hive.models.Owner;
import com.hive.services.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	@PostMapping("/register")
	public Owner register(@RequestBody Owner owner) {
		return ownerService.register(owner);
	}
	
	@GetMapping("/getOwner/{email}")
	public Owner getOwner(@PathVariable String email) {
		return ownerService.getOwner(email);
	}
	
	@PutMapping("/updateOwner")
	public Owner updateOwner(@RequestBody Owner owner) {
		return ownerService.updateOwner(owner);
	}

	@GetMapping("/getPhoto/{email}")
	public byte[] getPhoto(@PathVariable String email) {
		return ownerService.getPhoto(email);
	}
	
	@PostMapping("/manageBooking/{email}")
	public List<Booking> postMethodName(@PathVariable String email) {
		return ownerService.manageBooking(email);
	}
	

	@PutMapping("/updatePhoto")
	public Owner updatePhoto(@RequestBody Owner owner) {
		return ownerService.updatePhoto(owner);
	}

	@PutMapping(value = {"/updatePassword", "/forgetPassword"})
	public Owner updatePassword(@RequestBody Map<String, String> data) {
		String email = data.get("email");
		String newPassword = data.get("newPassword");
		return ownerService.updatePassword(email, newPassword);
	}
}
