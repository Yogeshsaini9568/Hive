package com.hive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.models.User;
import com.hive.repo.UserCustomRepo;

@Service
public class UserService {

	@Autowired
	UserCustomRepo userRepo;
	
	public boolean register(User user) {
		return userRepo.register(user);
	}

	public User getUser(String email) {
		return userRepo.getUser(email);
	}

	public User updateUser(User user) {
		return userRepo.updateUser(user);
	}

	public boolean updatePhoto(String email, byte[] photo) {
		return userRepo.updatePhoto(email, photo);
	}

	public byte[] getPhoto(String email) {
		return userRepo.getPhoto(email);
	}

	public boolean updatePassword(String userEmail, String newPassword) {
		return userRepo.updatePassword(userEmail,newPassword);
	}

}
