package com.jeevLifeWorks.main.exception;

public class InvalidInputDataException extends RuntimeException {

	/**
	 * Constructor a new InvalidInputDataException with specified details message
	 * 
	 * @param message the details message explaining reason for the message.
	 */
	public InvalidInputDataException(String message) {
		super(message);
	}
}
