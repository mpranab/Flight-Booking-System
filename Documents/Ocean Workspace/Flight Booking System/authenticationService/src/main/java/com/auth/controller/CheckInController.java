package com.auth.controller;
 
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
import org.springframework.web.bind.annotation.RestController;
import com.auth.client.CheckInClient;
import com.auth.entity.CheckIn;
 
@RestController
public class CheckInController {

 
	@Autowired
    private CheckInClient checkInService;
 
    @PostMapping("/api/checkIn/performCheckIn")
    public CheckIn createCheckIn(@RequestBody CheckIn checkIn) {
        return checkInService.createCheckIn(checkIn);
    }
 
    @GetMapping("/api/checkIn")
    public List<CheckIn> getAllCheckIns() {
        return checkInService.getAllCheckIns();
    }
 
    @GetMapping("/api/checkIn/{checkIn_id}")
    public ResponseEntity<CheckIn> getCheckInById(@PathVariable("checkIn_id") String id) {
        return checkInService.getCheckInById(id);
    }
 
    @GetMapping("/api/checkIn/booking/{bookingId}")
    public CheckIn getCheckInsByBookingId(@PathVariable("bookingId") String bookingId) {
        return checkInService.getCheckInsByBookingId(bookingId);
    }
 
    @GetMapping("/api/checkIn/checkin-status/{bookingId}")
    public ResponseEntity<Boolean> isCheckInDoneForBooking(@PathVariable String bookingId) {
        return checkInService.isCheckInDoneForBooking(bookingId);
    }
 
    @PutMapping("/api/checkIn/{id}")
    public ResponseEntity<CheckIn> updateCheckIn(@PathVariable("id") String id, @RequestBody CheckIn checkInDetails) {
        return checkInService.updateCheckIn(id, checkInDetails);
    }
 
    @DeleteMapping("/api/checkIn/{id}")
    public ResponseEntity<?> deleteCheckIn(@PathVariable("id") String id) {
        checkInService.deleteCheckIn(id);
        return new ResponseEntity<>("Deleted Succesfully", HttpStatus.OK);
    }
}