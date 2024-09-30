package com.checkIn.service;

import java.util.List;
import java.util.Optional;

import com.checkIn.entity.CheckIn;

public interface CheckInService {

	public CheckIn createCheckIn(CheckIn checkIn);

	public List<CheckIn> getAllCheckIns();

	public Optional<CheckIn> getCheckInById(String id);

	public CheckIn getCheckInsByBookingId(String bookingId);

	public CheckIn updateCheckIn(String bookingId, CheckIn checkInDetails);

	void deleteCheckIn(String bookingId);

	boolean isCheckInDoneForBooking(String bookingId);

}
