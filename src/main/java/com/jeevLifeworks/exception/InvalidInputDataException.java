package com.jeevLifeworks.exception;

/**
 * InvalidInputDataException is thrown when invalid data are inserted into
 * database. This Exception is used to indicate that provided input data does
 * not meet required validation.
 */
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
