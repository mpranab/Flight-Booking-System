package com.bookTicket.service;

import java.util.List;

import com.bookTicket.entity.Booking;

public interface BookingService {

	List<Booking> getAllBookings();

	Booking addBooking(Booking booking);

	Booking getBookingById(String bookingId);

	List<Booking> getBookingByFlightNo(Long flightNo);

	void deleteBookingById(String bookingId);

	Booking updateBookingById(Booking booking);

}
