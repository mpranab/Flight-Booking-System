package com.checkIn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checkIn.entity.CheckIn;
import com.checkIn.exception.BookingNotFoundException;
import com.checkIn.repository.CheckInRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CheckInServiceImpl implements CheckInService {

	@Autowired
	private CheckInRepository checkInRepository;

	@Override
	public CheckIn createCheckIn(CheckIn checkIn) {
		log.info("Creating new check-in for bookingId: {}", checkIn.getBookingId());
		CheckIn savedCheckIn = checkInRepository.save(checkIn);
		log.info("Check-in created successfully for bookingId: {}", savedCheckIn.getBookingId());
		return savedCheckIn;
	}

	@Override
	public List<CheckIn> getAllCheckIns() {
		log.info("Retrieving all check-ins");
		return checkInRepository.findAll();
	}

	@Override
	public Optional<CheckIn> getCheckInById(String bookingId) {
		log.info("Fetching check-in details for bookingId: {}", bookingId);
		return checkInRepository.findById(bookingId);
	}

	@Override
	public CheckIn getCheckInsByBookingId(String bookingId) {
		log.info("Fetching check-in by bookingId: {}", bookingId);
		CheckIn checkIn = checkInRepository.findByBookingId(bookingId);
		if (checkIn == null) {
			log.error("Check-in not found for bookingId: {}", bookingId);
			throw new BookingNotFoundException("Check-in not found for bookingId: " + bookingId);
		}
		log.info("Check-in retrieved successfully for bookingId: {}", bookingId);
		return checkIn;
	}

	@Override
	public CheckIn updateCheckIn(String bookingId, CheckIn checkInDetails) {
		log.info("Updating check-in for bookingId: {}", bookingId);
		CheckIn checkIn = checkInRepository.findById(bookingId).orElseThrow(() -> {
			log.error("Check-in not found for bookingId: {}", bookingId);
			return new BookingNotFoundException("Check-in not found for this id: " + bookingId);
		});

		checkIn.setCheckedIn(checkInDetails.isCheckedIn());
		checkIn.setSeats(checkInDetails.getSeats());
		checkIn.setBookingId(checkInDetails.getBookingId());

		CheckIn updatedCheckIn = checkInRepository.save(checkIn);
		log.info("Check-in updated successfully for bookingId: {}", bookingId);
		return updatedCheckIn;
	}

	@Override
	public void deleteCheckIn(String bookingId) {
		log.info("Deleting check-in for bookingId: {}", bookingId);
		CheckIn checkIn = checkInRepository.findById(bookingId).orElseThrow(() -> {
			log.error("Check-in not found for bookingId: {}", bookingId);
			return new BookingNotFoundException("Check-in not found for this bookingId: " + bookingId);
		});

		checkInRepository.delete(checkIn);
		log.info("Check-in deleted successfully for bookingId: {}", bookingId);
	}

	@Override
	public boolean isCheckInDoneForBooking(String bookingId) {
		log.info("Checking if check-in is done for bookingId: {}", bookingId);
		CheckIn checkIn = checkInRepository.findByBookingId(bookingId);

		boolean isCheckedIn = checkIn != null && checkIn.isCheckedIn();
		log.info("Check-in status for bookingId {}: {}", bookingId, isCheckedIn ? "Checked In" : "Not Checked In");
		return isCheckedIn;
	}
}
