package com.checkIn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.checkIn.entity.CheckIn;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, String> {

	boolean existsByBookingId(String bookingId);

	CheckIn findByBookingId(String bookingId);
}
