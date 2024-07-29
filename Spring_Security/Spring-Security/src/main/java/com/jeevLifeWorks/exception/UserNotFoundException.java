package com.jeevLifeWorks.exception;

/**
 * Custom exception to be thrown when a user is not found in the system.
 */
public class UserNotFoundException extends RuntimeException {

	// default constructor
	public UserNotFoundException() {
		super();
	}

	// Constructor with a custom message.
	public UserNotFoundException(String message) {
		super(message);
	}
}
