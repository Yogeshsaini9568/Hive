package com.hive.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hive.models.User;
import com.hive.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean register(User user) {
		// Hash password before saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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
		// Hash new password before updating
		String hashedPassword = passwordEncoder.encode(newPassword);
		return userRepo.updatePassword(userEmail, hashedPassword);
	}

}
