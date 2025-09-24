package com.hive.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hive.models.Booking;
import com.hive.repo.BookingRepo;

@Service
public class BookingService {

	@Autowired
	BookingRepo bookingRepo;
	
	public Booking book(Booking booking) {
		booking.setBookingDate(new Date(0));
		return bookingRepo.save(booking);
	}

	public List<Booking> getBookings(String userEmail) {
		return bookingRepo.findAllByUserEmail(userEmail);
	}

	
}
