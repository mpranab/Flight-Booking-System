package com.flight.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.entity.Flight;
import com.flight.Exception.*;
import com.flight.repository.FlightRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepo;

	@Override
	public Flight addFlight(Flight flight) throws ArrivalDepartureSameException, FlightAlreadyExistException {
		// TODO Auto-generated method stub
		if (flightRepo.existsById(flight.getFlightNo())) {
			throw new FlightAlreadyExistException("Flight Number Already Exists");
		}
		if (flight.getDepartureCity().equalsIgnoreCase(flight.getArrivalCity())) {
			log.error("Arrival and Departure city should not be same");
			throw new ArrivalDepartureSameException("Arrival and Departure city should not be same");
		}
		log.info("Flight information saved successfully");
		return flightRepo.save(flight);
	}

	@Override
	public List<Flight> searchFlightByCity(String departCity, String arrivalCity) throws FlightNotFoundException {

		log.info("Searching Flight between {} and {}",departCity,arrivalCity);
		List<Flight> flights = flightRepo.findByDepartureCityIgnoreCaseAndArrivalCityIgnoreCase(departCity,
				arrivalCity);
		if (flights.size() == 0) {
			log.error("Flights not found");
			throw new FlightNotFoundException("Flights not found");
		} else {
			log.info("Flights fetched successfully");
			return flights;
		}

	}

	@Override
	public Flight findByNumber(long flightNo) throws FlightNotFoundException {
		// TODO Auto-generated method stub
		Optional<Flight> flight = flightRepo.findById(flightNo);
		if (flight.isPresent()) {
			log.info("Flight information " + flight);
			return flight.get();
		} else {
			log.error("Flight not exist");
			throw new FlightNotFoundException("Flight not exist");
		}

	}

	@Override
	public List<Flight> getAll() {
		// TODO Auto-generated method stub
		log.info("Flights fetched");
		return flightRepo.findAll();
	}

	@Override
	public boolean deleteById(long flightNo) throws FlightNotFoundException {
		// TODO Auto-generated method stub
		Optional<Flight> flight = flightRepo.findById(flightNo);
		if (flight.isEmpty()) {
			log.error("Flight not exist");
			return false;
		}
		flightRepo.deleteById(flightNo);
		log.info("Flight deleted successfully");
		return true;
	}

	@Override
	public Flight updateFlight(Flight flight) throws FlightNotFoundException {
		// TODO Auto-generated method stub
		Optional<Flight> obj = flightRepo.findById(flight.getFlightNo());
		if (obj.isPresent()) {
			Flight old = obj.get();
			flightRepo.delete(old);
			log.info("Flight updated successfully");
			return flightRepo.save(flight);
		}
		log.error("Flight not exist");
		throw new FlightNotFoundException("Flight not exist");
	}

	@Override
	public List<Flight> findFlightsBetweenCitiesOnDate(String departureCity, String arrivalCity, Date departureDate) {
		// TODO Auto-generated method stub
		log.info("Searching flights between {} and {} on date {}", departureCity, arrivalCity, departureDate);
		List<Flight> flightsByDate = flightRepo.findFlightsBetweenCitiesOnDate(departureCity, arrivalCity, departureDate);
		if (flightsByDate.size() == 0) {
			log.error("Flights not found between {} and {} on date {}", departureCity, arrivalCity, departureDate);
			throw new FlightNotFoundException("Flights not found");
		} else {
			log.info("Flights fetched successfully");
			return flightsByDate;
		}
	}
}
