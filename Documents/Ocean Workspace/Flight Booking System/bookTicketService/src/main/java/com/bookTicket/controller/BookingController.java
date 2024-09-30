package com.bookTicket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookTicket.entity.Booking;
import com.bookTicket.service.BookingService;

@RestController
@RequestMapping("/api")
public class BookingController {

	@Autowired
	BookingService bookService;

	@GetMapping("/booking")
	public List<Booking> getAllBookings() {
		return bookService.getAllBookings();
	}

	@PostMapping("/booking/add")
	public Booking addBooking(@RequestBody Booking booking) {
		return bookService.addBooking(booking);
	}

	@GetMapping("/booking/{bookingId}")
	public Booking getBookingById(@PathVariable String bookingId) {
		return bookService.getBookingById(bookingId);

	}

	@GetMapping("/booking/{flightNo}")
	public List<Booking> getBookingIdByFlightNo(@PathVariable Long flightNo) {
		return bookService.getBookingByFlightNo(flightNo);
	}

	@DeleteMapping("/booking/{bookingId}")
	public String deleteBookingByBookingId(@PathVariable String bookingId) {
		bookService.deleteBookingById(bookingId);
		return "Deleted Successfully";
	}

	@PutMapping("/booking/{bookingId}")
	public Booking updateBookingById(@PathVariable String bookingId, @RequestBody Booking updateBooking) {
		return bookService.updateBookingById(updateBooking);
	}

}
