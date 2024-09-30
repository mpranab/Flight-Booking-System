package com.checkIn.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkIn.entity.CheckIn;
import com.checkIn.exception.BookingNotFoundException;
import com.checkIn.service.CheckInService;

@RestController
@RequestMapping("/api/checkIn")
public class CheckInController {

	@Autowired
	private CheckInService checkInService;

	@PostMapping("/performCheckIn")
	public CheckIn createCheckIn(@RequestBody CheckIn checkIn) {
		return checkInService.createCheckIn(checkIn);
	}

	@GetMapping
	public List<CheckIn> getAllCheckIns() {
		return checkInService.getAllCheckIns();
	}

	@GetMapping("/{checkIn_id}")
	public ResponseEntity<CheckIn> getCheckInById(@PathVariable("checkIn_id") String id) {
		CheckIn checkIn = checkInService.getCheckInById(id)
				.orElseThrow(() -> new BookingNotFoundException("CheckIn not found for this id :: " + id));
		return ResponseEntity.ok().body(checkIn);
	}

	@GetMapping("/booking/{bookingId}")
	public CheckIn getCheckInsByBookingId(@PathVariable("bookingId") String bookingId) {
		return checkInService.getCheckInsByBookingId(bookingId);
	}

	@GetMapping("/checkin-status/{bookingId}")
	public ResponseEntity<Boolean> isCheckInDoneForBooking(@PathVariable String bookingId) {
		boolean isCheckedIn = checkInService.isCheckInDoneForBooking(bookingId);
		return ResponseEntity.ok(isCheckedIn);
	}

	@PutMapping("/{bookingId}")
	public ResponseEntity<CheckIn> updateCheckIn(@PathVariable("bookingId") String bookingId, @RequestBody CheckIn checkInDetails) {
		CheckIn updatedCheckIn = checkInService.updateCheckIn(bookingId, checkInDetails);
		return ResponseEntity.ok(updatedCheckIn);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCheckIn(@PathVariable("bookingId") String bookingId) {
		checkInService.deleteCheckIn(bookingId);
		return new ResponseEntity<>("Deleted Succesfully", HttpStatus.OK);
	}
}
