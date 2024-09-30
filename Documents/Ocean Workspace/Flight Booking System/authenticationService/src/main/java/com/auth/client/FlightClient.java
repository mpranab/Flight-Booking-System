package com.auth.client;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.auth.entity.Flight;
import com.auth.exceptionHandler.ArrivalDepartureSameException;
import com.auth.exceptionHandler.FlightAlreadyExistException;
import com.auth.exceptionHandler.FlightNotFoundException;

import jakarta.validation.Valid;

@FeignClient(name = "flightservice", url = "http://localhost:8082/")
public interface FlightClient {

	@PostMapping("/flight/addFlight")
	public Flight addFlight(@Valid @RequestBody Flight flight)
			throws ArrivalDepartureSameException, FlightAlreadyExistException;

	@GetMapping("/flight/searchByCity/{departCity}/{arrivalCity}")
	public List<Flight> searchFlight(@PathVariable String departCity, @PathVariable String arrivalCity)
			throws FlightNotFoundException;

	@GetMapping("/flight/findByNumber/{flightNo}")
	public Flight getByFlightNo(@PathVariable long flightNo) throws FlightNotFoundException;

	@GetMapping("/flight")
	public List<Flight> findAll();

	@GetMapping("/flight/search/{departureCity}/{arrivalCity}/{departureDate}")
	public List<Flight> findFlightsBetweenCitiesOnDate(@PathVariable String departureCity,
			@PathVariable String arrivalCity, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate);

	@DeleteMapping("/flight/deleteById/{flightNo}")
	public boolean deleteById(@PathVariable long flightNo) throws FlightNotFoundException;

	@PutMapping("/flight/update")
	public Flight updateFlight(@RequestBody Flight flight) throws FlightNotFoundException;

}
