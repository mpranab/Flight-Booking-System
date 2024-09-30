package com.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.client.BookingClient;
import com.auth.entity.Booking;


@RestController

public class BookingController {

	@Autowired
	private BookingClient bookingService;

	@GetMapping("/api/booking")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@PostMapping("/api/booking/add")
	public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
		return bookingService.addBooking(booking);
	}

	@GetMapping("/api/booking/{bookingId}")
	public Booking getBookingById(@PathVariable String bookingId) {
		return bookingService.getBookingById(bookingId);
	}

	@GetMapping("/api/booking/{flightNo}")
	public List<Booking> getBookingByFlightNo(@PathVariable Long flightNo) {
		return bookingService.getBookingByFlightNo(flightNo);
	}

	@DeleteMapping("/api/booking/{bookingId}")
	public ResponseEntity<?> deleteBookingById(@PathVariable String bookingId) {
		bookingService.deleteBookingById(bookingId);
		return new ResponseEntity<>("Deleted Succesfully", HttpStatus.OK);
	}

	@PutMapping("/api/booking/{bookingId}")
	public Booking updateBookingById(@PathVariable String bookingId, @RequestBody Booking updatedBooking) {
		return bookingService.updateBookingById(bookingId, updatedBooking);
	}
}