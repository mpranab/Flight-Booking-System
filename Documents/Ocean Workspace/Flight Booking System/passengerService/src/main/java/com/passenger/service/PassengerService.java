package com.passenger.service;

import java.util.List;

import com.passenger.entity.Passenger;

public interface PassengerService {

	public Passenger addPassenger(Passenger passenger);

	public Passenger getPassenger(String passenger);

	public List<Passenger> getAllPassenger();

	public String deletePassenger(String passengerId);

	public String delete(int seatno, long flightNo);

	public long count(long flightNo);

}
