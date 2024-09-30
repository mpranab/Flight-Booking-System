package com.bookTicket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookTicket.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

	List<Booking> findByFlightNo(Long flightNo);

	
}
