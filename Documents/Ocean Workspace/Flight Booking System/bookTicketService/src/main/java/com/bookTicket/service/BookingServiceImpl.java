package com.bookTicket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookTicket.entity.Booking;
import com.bookTicket.exception.BookingNotFoundException;
import com.bookTicket.exception.SeatAlreadyBookedException;
import com.bookTicket.repository.BookingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookRepo;

	@Override
	 public List<Booking> getAllBookings() {
        log.info("Fetching all bookings...");
        List<Booking> bookings = bookRepo.findAll();
        log.info("Total bookings found: {}", bookings.size());
        return bookings;
    }

	@Override
	 public Booking addBooking(Booking booking) {
        log.info("Adding booking for flightNo: {}", booking.getFlightNo());
        for (Integer seat : booking.getSeats()) {
            List<Booking> existingBookings = bookRepo.findByFlightNo(booking.getFlightNo());
            for (Booking existingBooking : existingBookings) {
                if (existingBooking.getSeats().contains(seat)) {
                    log.error("Seat {} is already booked for flight {}", seat, booking.getFlightNo());
                    throw new SeatAlreadyBookedException(
                            "Seat " + seat + " is already booked for flight " + booking.getFlightNo());
                }
            }
        }
        Booking savedBooking = bookRepo.save(booking);
        log.info("Booking saved successfully with bookingId: {}", savedBooking.getBookingId());
        return savedBooking;
    }

	@Override
	public Booking getBookingById(String bookingId) {
        log.info("Fetching booking with bookingId: {}", bookingId);
        return bookRepo.findById(bookingId).orElseThrow(() -> {
            log.error("Booking not found with bookingId: {}", bookingId);
            return new BookingNotFoundException("Booking not found with bookingId: " + bookingId);
        });
    }

	@Override
	public List<Booking> getBookingByFlightNo(Long flightNo) {
        log.info("Fetching bookings for flightNo: {}", flightNo);
        List<Booking> bookings = bookRepo.findByFlightNo(flightNo);
        log.info("Total bookings found for flightNo {}: {}", flightNo, bookings.size());
        return bookings;
    }

	@Override
	public void deleteBookingById(String bookingId) {
        log.info("Deleting booking with bookingId: {}", bookingId);
        if (bookRepo.existsById(bookingId)) {
            bookRepo.deleteById(bookingId);
            log.info("Booking deleted successfully with bookingId: {}", bookingId);
        } else {
            log.error("Booking not found for deletion with bookingId: {}", bookingId);
            throw new BookingNotFoundException("Booking not found for deletion with bookingId: " + bookingId);
        }
    }

	@Override
	public Booking updateBookingById(Booking updatedBooking) {
        log.info("Updating booking with bookingId: {}", updatedBooking.getBookingId());
        if (bookRepo.existsById(updatedBooking.getBookingId())) {
            Booking savedBooking = bookRepo.save(updatedBooking);
            log.info("Booking updated successfully with bookingId: {}", savedBooking.getBookingId());
            return savedBooking;
        } else {
            log.error("Booking not found for update with bookingId: {}", updatedBooking.getBookingId());
            throw new BookingNotFoundException("Booking not found for update with bookingId: " + updatedBooking.getBookingId());
        }
    }

}
