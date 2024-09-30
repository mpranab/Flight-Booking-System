package com.auth.exceptionHandler;

public class FlightNotFoundException extends RuntimeException {
	public FlightNotFoundException(String msg) {
		super(msg);
	}

}