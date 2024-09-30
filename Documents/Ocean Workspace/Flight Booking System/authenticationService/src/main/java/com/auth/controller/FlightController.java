package com.auth.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.client.FlightClient;

import jakarta.validation.Valid;
import com.auth.exceptionHandler.ArrivalDepartureSameException;
import com.auth.exceptionHandler.FlightAlreadyExistException;
import com.auth.exceptionHandler.FlightNotFoundException;
import com.auth.entity.Flight;

@RestController
public class FlightController {

	@Autowired
	private FlightClient flightService;

	@PostMapping("/flight/addFlight")
	public Flight addFlight(@Valid @RequestBody Flight flight)
			throws ArrivalDepartureSameException, FlightAlreadyExistException {
		return flightService.addFlight(flight);
	}

	@GetMapping("/flight/searchByCity/{departCity}/{arrivalCity}")
	public List<Flight> searchFlight(@PathVariable String departCity, @PathVariable String arrivalCity)
			throws FlightNotFoundException {
		return flightService.searchFlight(departCity, arrivalCity);
	}

	@GetMapping("/flight/findByNumber/{flightNo}")
	public Flight getByFlightNo(@PathVariable long flightNo) throws FlightNotFoundException {
		return flightService.getByFlightNo(flightNo);
	}

	@GetMapping("/flight/findAll")
	public List<Flight> findAll() {
		return flightService.findAll();
	}

	@GetMapping("/flight/search/{departureCity}/{arrivalCity}/{departureDate}")
	public List<Flight> findFlightsBetweenCitiesOnDate(@PathVariable String departureCity,
			@PathVariable String arrivalCity,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate) {
		return flightService.findFlightsBetweenCitiesOnDate(departureCity, arrivalCity, departureDate);
	}

	@DeleteMapping("/flight/deleteById/{flightNo}")
	public boolean deleteById(@PathVariable long flightNo) throws FlightNotFoundException {
		return flightService.deleteById(flightNo);
	}

	@PutMapping("/flight/update")
	public Flight updateFlight(@RequestBody Flight flight) throws FlightNotFoundException {
		return flightService.updateFlight(flight);
	}
}