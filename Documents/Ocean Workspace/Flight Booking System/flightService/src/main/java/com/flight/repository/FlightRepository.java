package com.flight.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.flight.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findByDepartureCityIgnoreCaseAndArrivalCityIgnoreCase(String departCity, String arrivalCity);

	@Query("SELECT f FROM Flight f WHERE f.departureCity = :departureCity AND f.arrivalCity = :arrivalCity AND FUNCTION('DATE', f.departureDate) = :departureDate")
	List<Flight> findFlightsBetweenCitiesOnDate(@Param("departureCity") String departureCity,
			@Param("arrivalCity") String arrivalCity,
			@Param("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate);

}
