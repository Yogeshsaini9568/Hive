package com.hive.repo;



import java.sql.Date;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hive.models.Owner;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class OwnerRepoCustom {

	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public boolean register(Owner owner) {
		Session session=entityManager.unwrap(Session.class);
		Owner o = session.get(Owner.class, owner.getEmail());
		if(o==null) {
			owner.setRegistrationDate(new Date(0));
			session.persist(owner);
			return true;
		}else {
			return false;
		}
	}

	public Owner getOwner(String email) {
		Session session=entityManager.unwrap(Session.class);
		Owner o = session.get(Owner.class, email);
		return o;
	}

//	@Transactional
//	public Owner updateOwner(Owner owner) {
//		Session session = entityManager.unwrap(Session.class);
//		Owner o = session.get(Owner.class, owner.getOwnerEmail());
//		if (o != null) {
//			o.setOwnerName(owner.getOwnerName());
//			o.setOwnerDob(owner.getOwnerDob());
//			o.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());
//			o.setOwnerGender(owner.getOwnerGender());
//			o.setOwnerCountry(owner.getOwnerCountry());
//			o.setOwnerState(owner.getOwnerState());
//			o.setOwnerCity(owner.getOwnerCity());
//			o.setOwnerStreet(owner.getOwnerStreet());
//			o.setOwnerPincode(owner.getOwnerPincode());
//			session.persist(o);
//		}
//		return o;
//	}
//
//	@Transactional
//	public boolean updatePhoto(String email, byte[] photo) {
//		Session session = entityManager.unwrap(Session.class);
//		Owner o = session.get(Owner.class, email);
//		if (o != null) {
//			o.setOwnerImg(photo);
//			session.persist(o);
//			return true;
//		} else {
//			return false;
//		}
//	}
//
	public byte[] getPhoto(String email) {
		Session session = entityManager.unwrap(Session.class);
		Owner o = session.get(Owner.class, email);
		return o.getPhoto();
	}
//
//	@Transactional
//	public boolean updatePassword(String ownerEmail, String newPassword) {
//		Session session = entityManager.unwrap(Session.class);
//		Owner o = session.get(Owner.class, ownerEmail);
//		if (o != null) {
//			o.setOwnerPassword(newPassword);
//			o.setOwnerLastTimePasswordChange(new Date());
//			session.persist(o);
//			return true;
//		} else {
//			return false;
//		}
//	}


}
