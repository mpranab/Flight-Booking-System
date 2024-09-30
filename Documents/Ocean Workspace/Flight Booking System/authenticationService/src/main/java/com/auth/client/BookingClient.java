package com.auth.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.auth.entity.Booking;

@FeignClient(name="bookTicketService", url="http://localhost:8081/")
public interface BookingClient {
 
	
	@GetMapping("/api/booking")
    public List<Booking> getAllBookings() ;
 
    @PostMapping("/api/booking/add")
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) ;
 
    @GetMapping("/api/booking/flight/{bookingId}")
    public Booking getBookingById(@PathVariable String bookingId) ;
 
    @GetMapping("/api/booking/flight/{flightNo}")
    public List<Booking> getBookingByFlightNo(@PathVariable Long flightNo) ;
 
    @DeleteMapping("/api/booking/{bookingId}")
    public ResponseEntity<?> deleteBookingById(@PathVariable String bookingId) ;
 
    @PutMapping("/api/booking/{bookingId}")
    public Booking updateBookingById(@PathVariable String bookingId, @RequestBody Booking updatedBooking) ;
}
