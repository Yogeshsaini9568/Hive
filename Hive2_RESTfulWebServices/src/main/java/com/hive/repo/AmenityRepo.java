package com.hive.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hive.models.Amenity;

public interface AmenityRepo extends JpaRepository<Amenity, Integer>{

}
