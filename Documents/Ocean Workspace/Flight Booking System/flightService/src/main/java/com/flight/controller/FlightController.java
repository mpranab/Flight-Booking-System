package com.flight.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.entity.Flight;
import com.flight.Exception.*;
import com.flight.service.FlightService;


@RestController
@RequestMapping("/flight")
@CrossOrigin(origins="*")
public class FlightController {

	@Autowired
	FlightService flightService;
	
	
	@PostMapping("/addFlight")
	public Flight addFlight(@RequestBody Flight flight) throws ArrivalDepartureSameException, FlightAlreadyExistException {
		return flightService.addFlight(flight);
	}
	
	@GetMapping("/searchByCity/{departCity}/{arrivalCity}")
	public List<Flight> searchFlight(
			@PathVariable String departCity,
			@PathVariable String arrivalCity)throws FlightNotFoundException{
		return flightService.searchFlightByCity(departCity, arrivalCity);
	}
	
	@GetMapping("/findByNumber/{flightNo}")
	public Flight getByFlightNo(@PathVariable long flightNo) throws FlightNotFoundException{
		return flightService.findByNumber(flightNo);
	}
	
	@GetMapping
	public List<Flight> findAll(){
		return flightService.getAll();
	}
	
	@GetMapping("/search/{departureCity}/{arrivalCity}/{departureDate}")
    public List<Flight> findFlightsBetweenCitiesOnDate(
            @PathVariable String departureCity,
            @PathVariable String arrivalCity,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate) {
        return flightService.findFlightsBetweenCitiesOnDate(departureCity, arrivalCity, departureDate);
    }
	
	
	@DeleteMapping("/deleteById/{flightNo}")
	public boolean deleteById(@PathVariable long flightNo) throws FlightNotFoundException{
		return flightService.deleteById(flightNo);
	}
	
	@PutMapping("/update")
	public Flight updateFlight(@RequestBody Flight flight) throws FlightNotFoundException{
		return flightService.updateFlight(flight);
	}

}
