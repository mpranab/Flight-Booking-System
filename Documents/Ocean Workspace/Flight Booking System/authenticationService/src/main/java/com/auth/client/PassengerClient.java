package com.auth.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.auth.entity.Passenger;

import jakarta.validation.Valid;

@FeignClient(name = "passengerService", url = "http://localhost:8082/")
public interface PassengerClient {
	@PostMapping("/api/passenger/addPassenger")
	public Passenger addPassenger(@Valid @RequestBody Passenger passenger);

	@GetMapping("/api/passenger/getAll")
	public List<Passenger> getPassenger();

	@DeleteMapping("/api/passenger/deleteById/{passengerId}")
	public String deletePassenger(@PathVariable String passengerId);

	@DeleteMapping("/api/passenger/delete/{seat}/{flightNo}")
	public String delete(@PathVariable int seat, @PathVariable long flightNo);

	@GetMapping("/api/passenger/count/{flightNo}")
	public long count(@PathVariable long flightNo);

}
