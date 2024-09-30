package com.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "checkIn_Service")
public class CheckIn {

	@Id
	private String checkInid;
	private boolean checkedIn;
	private int seats;
	private String bookingId;
	
	public String getCheckInid() {
		return checkInid;
	}
	public void setCheckInid(String checkInid) {
		this.checkInid = checkInid;
	}
	public boolean isCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	

}
