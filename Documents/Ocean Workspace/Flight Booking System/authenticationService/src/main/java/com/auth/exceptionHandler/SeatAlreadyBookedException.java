package com.auth.exceptionHandler;

public class SeatAlreadyBookedException extends RuntimeException {
	public SeatAlreadyBookedException(String msg) {
		super(msg);
	}

}