package com.flight.service;

import java.util.Date;
import java.util.List;

import com.flight.Exception.ArrivalDepartureSameException;
import com.flight.Exception.FlightAlreadyExistException;
import com.flight.Exception.FlightNotFoundException;
import com.flight.entity.Flight;

public interface FlightService {
	
	public Flight addFlight(Flight flight)throws ArrivalDepartureSameException, FlightAlreadyExistException;
	
	public List<Flight> searchFlightByCity(String departCity, String arrivalCity) throws FlightNotFoundException;
	
	public Flight findByNumber(long flightNo) throws FlightNotFoundException;
	
	public List<Flight> getAll();
	
	public boolean deleteById(long flightNo)throws FlightNotFoundException;
	
	public Flight updateFlight(Flight flight)throws FlightNotFoundException;
	
	public List<Flight> findFlightsBetweenCitiesOnDate(String departureCity, String arrivalCity, Date departureDate); 
        
    

	

}

