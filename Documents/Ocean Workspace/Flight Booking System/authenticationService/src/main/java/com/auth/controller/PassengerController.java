package com.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.auth.entity.Passenger;
import com.auth.client.PassengerClient;

import jakarta.validation.Valid;

@RestController
public class PassengerController {
	@Autowired
	public PassengerClient service;

	@PostMapping("/api/passenger/addPassenger")
	public Passenger addPassenger(@Valid @RequestBody Passenger passenger) {
		return service.addPassenger(passenger);
	}

	@GetMapping("/api/passenger/getAll")
	public List<Passenger> getPassenger() {
		return service.getPassenger();
	}

	@DeleteMapping("/api/passenger/deleteById/{passengerId}")
	public String deletePassenger(@PathVariable String passengerId) {
		return this.service.deletePassenger(passengerId);
	}

	@DeleteMapping("/api/passenger/delete/{seat}/{flightNo}")
	public String delete(@PathVariable int seat, @PathVariable long flightNo) {
		return this.service.delete(seat, flightNo);
	}

	@GetMapping("/api/passenger/count/{flightNo}")
	public long count(@PathVariable long flightNo) {
		return this.service.count(flightNo);
	}

}