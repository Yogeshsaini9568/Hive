package com.hive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.hive.models.Owner;
import com.hive.repo.OwnerRepo;
import com.hive.repo.OwnerRepoCustom;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepoCustom ownerRepoCustom;
	
	@Autowired
	private OwnerRepo ownerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Owner register(Owner owner) {
		// Hash password before saving
		owner.setPassword(passwordEncoder.encode(owner.getPassword()));
		return ownerRepo.save(owner);
	}

	public Owner getOwner(String email) {
		return ownerRepo.findById(email).orElseThrow();
	}
	
//	public Owner updateOwner(Owner owner) {
//		return ownerRepo.updateOwner(owner);
//	}
//
//	public boolean updatePhoto(String email, byte[] photo) {
//		return ownerRepo.updatePhoto(email, photo);
//	}
//
	public byte[] getPhoto(String email) {
		return ownerRepoCustom.getPhoto(email);
	}
//
//	public boolean updatePassword(String ownerEmail, String newPassword) {
//		return ownerRepo.updatePassword(ownerEmail,newPassword);
//	}

}
