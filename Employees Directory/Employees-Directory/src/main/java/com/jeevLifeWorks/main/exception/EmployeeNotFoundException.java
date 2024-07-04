package com.jeevLifeWorks.main.exception;

public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * Constructor a new EmployeeNotFoundException with specified details message
	 * 
	 * @param message the details message explaining reason for the message.
	 */
	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
