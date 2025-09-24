package com.hive.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.models.Booking;
import com.hive.models.Owner;
import com.hive.repo.BookingRepo;
import com.hive.repo.OwnerRepo;
import com.hive.repo.OwnerRepoCustom;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepoCustom ownerRepoCustom;
	
	@Autowired
	private OwnerRepo ownerRepo;
	
	@Autowired
	BookingRepo bookingRepo;
	
	public Owner register(Owner owner) {
		owner.setRegistrationDate(new Date(0));
		return ownerRepo.save(owner);
	}

	public Owner getOwner(String email) {
		return ownerRepo.findById(email).orElseThrow();
	}
	
	public Owner updateOwner(Owner owner) {
		return ownerRepoCustom.updateOwner(owner);
	}

	public Owner updatePhoto(Owner owner) {
		return ownerRepoCustom.updatePhoto(owner);
	}

	public byte[] getPhoto(String email) {
		return ownerRepoCustom.getPhoto(email);
	}

	public Owner updatePassword(String email, String newPassword) {
		return ownerRepoCustom.updatePassword(email,newPassword);
	}

	public List<Booking> manageBooking(String email) {
		return bookingRepo.findAllByOwnerEmail(email);
	}

}
