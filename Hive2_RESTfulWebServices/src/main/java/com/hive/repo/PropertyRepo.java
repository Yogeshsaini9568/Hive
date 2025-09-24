package com.hive.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hive.models.Property;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer>{

	List<Property> findByOwnerEmail(String email);

	List<Property> findByNameContaining(String name);


}
