package com.auth.exceptionHandler;

public class FlightAlreadyExistException extends RuntimeException {

	public FlightAlreadyExistException(String msg) {
		super(msg);
	}

}