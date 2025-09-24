package com.hive.repo;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hive.models.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class UserRepo {

	@Autowired
	EntityManager entityManager;
	
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
		User u = session.get(User.class, user.getEmail());
		if(u!=null) {
			u.setName(user.getName());
			u.setPhone(user.getPhone());
			u.setGender(user.getGender());
			u.setDob(user.getDob());
			session.persist(u);
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
			//u.setUserLastTimePasswordChange(new Date());
			session.persist(u);
			return true;
		}else {
			return false;
		}
	}

}
