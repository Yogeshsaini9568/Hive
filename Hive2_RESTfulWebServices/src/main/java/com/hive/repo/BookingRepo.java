package com.hive.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hive.models.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

	List<Booking> findAllByUserEmail(String userEmail);

	List<Booking> findAllByOwnerEmail(String email);

}
