package com.hive.repo;



import java.sql.Date;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hive.models.Address;
import com.hive.models.Owner;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class OwnerRepoCustom {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	AddressRepo addressRepo;
	
	@Autowired
	OwnerRepo ownerRepo;
	
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

	@Transactional
	public Owner updateOwner(Owner owner) {
		Session session = entityManager.unwrap(Session.class);
		Address address=owner.getAddress();
		Owner o = session.get(Owner.class, owner.getEmail());
		if(o.getAddress()==null) {
			address=addressRepo.save(address);
			o.setAddress(address);
			o.setName(owner.getName());
			o.setDob(owner.getDob());
			o.setGender(owner.getGender());
			o.setPhone(owner.getPhone());
			o=ownerRepo.save(o);
		}else {
			Address a=session.get(Address.class, o.getAddress().getId());
			a.setHouseNo(address.getHouseNo());			
			a.setCity(address.getCity());
			a.setCountry(address.getCountry());
			a.setPincode(address.getPincode());
			a.setSector(address.getSector());
			a.setState(address.getState());
			addressRepo.save(a); //Save the Address
			
			o.setName(owner.getName());
			o.setDob(owner.getDob());
			o.setGender(owner.getGender());
			o.setPhone(owner.getPhone());
			o=ownerRepo.save(o);
		}
		return o;
	}

	@Transactional
	public Owner updatePhoto(Owner owner) {
		Session session = entityManager.unwrap(Session.class);
		Owner o = session.get(Owner.class, owner.getEmail());
		if (o != null) {
			o.setPhoto(owner.getPhoto());
			o=ownerRepo.save(o);
		} 
		return o;
	}

	public byte[] getPhoto(String email) {
		Session session = entityManager.unwrap(Session.class);
		Owner o = session.get(Owner.class, email);
		return o.getPhoto();
	}

	@Transactional
	public Owner updatePassword(String email, String newPassword) {
		Session session = entityManager.unwrap(Session.class);
		Owner o = session.get(Owner.class, email);
		if (o != null) {
			o.setPassword(newPassword);
			o=ownerRepo.save(o);
		} 
		return o;
	}


}
