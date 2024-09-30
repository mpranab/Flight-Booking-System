package com.passenger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passenger.entity.Passenger;
import com.passenger.service.PassengerService;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {

	@Autowired
	public PassengerService passService;

	@PostMapping("/addPassenger")
	public Passenger addPassenger(@RequestBody Passenger passenger) {

		return passService.addPassenger(passenger);
	}

	@GetMapping("/getAll")
	public List<Passenger> getPassenger() {
		return passService.getAllPassenger();
	}

	@DeleteMapping("/deleteById/{passengerId}")
	public String deletePassenger(@PathVariable String passengerId) {
		return this.passService.deletePassenger(passengerId);
	}

	@DeleteMapping("/delete/{seat}/{flightNo}")
	public String delete(@PathVariable int seatno, @PathVariable long flightNo) {
		return this.passService.delete(seatno, flightNo);
	}
	
	@GetMapping("/count/{flightNo}")
	public long count(@PathVariable long flightNo) {
		return this.passService.count(flightNo);
	}

}
