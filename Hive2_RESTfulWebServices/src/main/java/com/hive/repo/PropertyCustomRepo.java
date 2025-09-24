package com.hive.repo;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hive.models.Amenity;
import com.hive.models.Property;
import com.hive.models.Room;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class PropertyCustomRepo {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	AmenityRepo amenityRepo;
	
	@Autowired
	PropertyRepo propertyRepo;
	
	@Transactional
	public boolean addProperty(Property property) {
		Session session=entityManager.unwrap(Session.class);
		Property b = session.get(Property.class, property.getId());
		if(b==null) {
			session.persist(property);
			return true;
		}else {
			return false;
		}
	}

	@Transactional
	public Property addRoom(Room room) {
		Session session=entityManager.unwrap(Session.class);
		Amenity amenity=room.getAmenity();
		if(amenity.getId()==0) {
			amenity=amenityRepo.save(amenity);
		}
		room.setAmenity(amenity);
		session.persist(room);
		return null;
	}

	@Transactional
	public void uploadImages(Property property) {
		Session session=entityManager.unwrap(Session.class);
		Property p = session.get(Property.class, property.getId());
		if (p != null) {
			p.setImg1(property.getImg1());
			p.setImg2(property.getImg2());
			p.setImg3(property.getImg3());
			p.setImg4(property.getImg4());
			session.persist(p);
		}
		
	}

//	public Owner getOwner(String email) {
//		Session session=entityManager.unwrap(Session.class);
//		Owner o = session.get(Owner.class, email);
//		return o;
//	}
//
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
//	public byte[] getPhoto(String email) {
//		Session session = entityManager.unwrap(Session.class);
//		Owner o = session.get(Owner.class, email);
//		return o.getOwnerImg();
//	}
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
