package com.hive.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hive.models.Booking;

@Service
public class BookingService {

	private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://localhost:2003/booking";
    
	public Booking book(Booking booking) {
		String API="/book";
		HttpEntity<Booking> requestEntity=new HttpEntity<Booking>(booking);
		ResponseEntity<Booking> result = restTemplate.exchange(URL+API, HttpMethod.POST, requestEntity, Booking.class);
		Booking b=result.getBody();
		return b;
	}
	
	public List<Booking> getBookings(String userEmail){
		String API="/getBookings/"+userEmail;
		ResponseEntity<List<Booking>> result = restTemplate.exchange(URL+API, HttpMethod.POST, null, new ParameterizedTypeReference<List<Booking>>() {});
		List<Booking> bookings=result.getBody();
		return bookings;
	}
    
    
}
