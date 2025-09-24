package com.hive.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hive.models.Booking;
import com.hive.services.BookingService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/booking")
@Controller
public class BookingController {

	@Autowired
	BookingService bookingService;

	@PostMapping("/book")
	public String book(@ModelAttribute Booking booking,ModelMap m) {
		bookingService.book(booking);
		List<Booking> bookings=bookingService.getBookings(booking.getUserEmail());
		m.addAttribute("bookings", bookings);
		return "userBookings";
	}
	
}
