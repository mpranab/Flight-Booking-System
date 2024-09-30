package com.auth.exceptionHandler;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
	private final ErrorDecoder errorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		System.out.println("Error Response!!!");
		if (401 == response.status()) {
			System.out.println("It's a 400 Error!!!");
			return new ResourceNotFoundException("Resource not found error occurred");
		}
		if (response.status() == 400) {
			return new Exception("Bad request error occurred");
		} else if (response.status() == 404) {
			return new ResourceNotFoundException("Resource not found error occurred");
		}
		return errorDecoder.decode(methodKey, response);
	}
}
