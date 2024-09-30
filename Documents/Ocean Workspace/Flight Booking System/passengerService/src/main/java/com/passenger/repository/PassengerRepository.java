package com.passenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.passenger.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String>{

	Passenger findByFirstnameIgnoreCase(String firstname);

	Passenger findBySeatnoAndFlightNo(int seatno, long flightNo);

	long countByFlightNo(long flightNo);

}
