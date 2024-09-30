package com.passenger.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passenger.entity.Passenger;
import com.passenger.exception.PassengerNotFoundException;
import com.passenger.repository.PassengerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	PassengerRepository passRepo;

	@Override
	public Passenger addPassenger(Passenger passenger) {
		log.info("Adding passenger: {}", passenger);
		passenger.setPassengerId(UUID.randomUUID().toString());
		Passenger savedPassenger = passRepo.save(passenger);
		log.info("Passenger added successfully: {}", savedPassenger);
		return savedPassenger;
	}

	@Override
	public Passenger getPassenger(String passenger) {
        log.info("Fetching passenger with first name");
        Passenger passengers_firstName = passRepo.findByFirstnameIgnoreCase(passenger);
        if (passenger != null) {
            log.info("Passenger found: {}", passenger);
            return passengers_firstName;
        } else {
            log.error("Passenger with first name not found");
            throw new PassengerNotFoundException("Passenger with first name not found");
        }
    }

	@Override
	public List<Passenger> getAllPassenger() {
		log.info("Fetching all passengers");
		List<Passenger> passengers = passRepo.findAll();
		log.info("Total passengers fetched: {}", passengers.size());
		return passengers;
	}

	@Override
	public String deletePassenger(String passengerId) {
		log.info("Deleting passenger with ID: {}", passengerId);
		if (passRepo.existsById(passengerId)) {
			passRepo.deleteById(passengerId);
			log.info("Passenger with ID {} deleted successfully", passengerId);
			return "Deleted Successfully";
		} else {
			log.error("Passenger with ID {} not found", passengerId);
			throw new PassengerNotFoundException("Passenger with ID " + passengerId + " not found");
		}
	}

	@Override
	public String delete(int seat, long flightNo) {
		log.info("Attempting to delete passenger with seat number {} on flight number {}", seat, flightNo);
		Passenger passenger = passRepo.findBySeatnoAndFlightNo(seat, flightNo);
		if (passenger != null) {
			passRepo.delete(passenger);
			log.info("Passenger with seat number {} on flight number {} deleted successfully", seat, flightNo);
			return "Passenger deleted";
		} else {
			log.error("Passenger with seat number {} on flight number {} not found", seat, flightNo);
			throw new PassengerNotFoundException(
					"Passenger with seat number " + seat + " on flight number " + flightNo + " not found");
		}
	}

	@Override
	public long count(long flightNo) {
		// TODO Auto-generated method stub
		return this.passRepo.countByFlightNo(flightNo);
	}
}
