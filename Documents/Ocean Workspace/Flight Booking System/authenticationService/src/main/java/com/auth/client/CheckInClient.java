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

import com.auth.entity.CheckIn;

@FeignClient(name="checkinService", url="http://localhost:8085/")
public interface CheckInClient {
 
	
	@PostMapping("/api/checkIn/performCheckIn")
    public CheckIn createCheckIn(@RequestBody CheckIn checkIn) ;
 
    @GetMapping("/api/checkIn")
    public List<CheckIn> getAllCheckIns() ;
 
    @GetMapping("/api/checkIn/{checkIn_id}")
    public ResponseEntity<CheckIn> getCheckInById(@PathVariable("checkIn_id") String id) ;
 
    @GetMapping("/api/checkIn/booking/{bookingId}")
    public CheckIn getCheckInsByBookingId(@PathVariable("bookingId") String bookingId);
 
    @GetMapping("/api/checkIn/checkin-status/{bookingId}")
    public ResponseEntity<Boolean> isCheckInDoneForBooking(@PathVariable String bookingId) ;
 
    @PutMapping("/api/checkIn/{id}")
    public ResponseEntity<CheckIn> updateCheckIn(@PathVariable("id") String id, @RequestBody CheckIn checkInDetails) ;
    @DeleteMapping("/api/checkIn/{id}")
    public ResponseEntity<?> deleteCheckIn(@PathVariable("id") String id) ;
}
