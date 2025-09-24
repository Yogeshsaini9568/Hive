package com.hive.repo;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hive.models.Address;
import com.hive.models.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class UserCustomRepo {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AddressRepo addressRepo;
	
	@Transactional
	public boolean register(User user) {
		Session session=entityManager.unwrap(Session.class);
		User u = session.get(User.class, user.getEmail());
		if(u==null) {
			//user.setUserRegistrationDate(new Date()); 
			session.persist(user);
			return true;
		}else {
			return false;
		}
	}

	public User getUser(String email) {
		Session session=entityManager.unwrap(Session.class);
		User u = session.get(User.class, email);
		return u;
	}

	@Transactional
	public User updateUser(User user) {
		Session session = entityManager.unwrap(Session.class);
		Address address=user.getAddress();
		User u = session.get(User.class, user.getEmail());
		if(u.getAddress()==null) {
			address=addressRepo.save(address);
			u.setAddress(address);
			u.setName(user.getName());
			u.setPhone(user.getPhone());
			u.setGender(user.getGender());
			u.setDob(user.getDob());
			u= userRepo.save(u);
		}else {
			Address a=session.get(Address.class, u.getAddress().getId());
			a.setHouseNo(address.getHouseNo());			
			a.setCity(address.getCity());
			a.setCountry(address.getCountry());
			a.setPincode(address.getPincode());
			a.setSector(address.getSector());
			a.setState(address.getState());
			addressRepo.save(a); //Save the Address
			
			u.setName(user.getName());
			u.setPhone(user.getPhone());
			u.setGender(user.getGender());
			u.setDob(user.getDob());
			u= userRepo.save(u);
		}
		return u;
	}

	@Transactional
	public boolean updatePhoto(String email, byte[] photo) {
		Session session = entityManager.unwrap(Session.class);
		User u = session.get(User.class, email);
		if(u!=null) {
			u.setPhoto(photo);
			session.persist(u);
			return true;
		}else {
			return false;
		}
	}

	public byte[] getPhoto(String email) {
		Session session = entityManager.unwrap(Session.class);
		User u = session.get(User.class, email);
		return u.getPhoto();
	}
	
	@Transactional
	public boolean updatePassword(String userEmail, String newPassword) {
		Session session = entityManager.unwrap(Session.class);
		User u = session.get(User.class, userEmail);
		if(u!=null) {
			u.setPassword(newPassword);
			session.persist(u);
			return true;
		}else {
			return false;
		}
	}

}
