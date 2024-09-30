package com.checkIn.exception;

public class BookingNotFoundException extends RuntimeException{
	
	public BookingNotFoundException(String msg)
	{
		super(msg);
	}

}