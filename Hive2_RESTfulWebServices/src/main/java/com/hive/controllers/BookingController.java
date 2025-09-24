package com.hive.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hive.models.Booking;
import com.hive.services.BookingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/booking")
@RestController
public class BookingController {

	@Autowired
	BookingService 	bookingService; 
	
	@PostMapping("/book")
	public Booking book(@RequestBody Booking booking) {
		return bookingService.book(booking);
	}
	
	@PostMapping("/getBookings/{userEmail}")
	public List<Booking> getBookings(@PathVariable String userEmail) {
		return bookingService.getBookings(userEmail);
	}
	
	
}
