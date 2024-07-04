package com.jeevLifeworks.exception;

/**
 * StudentNotFoundException is thrown when the given ID is not present in the
 * database. This exception is used to indicate that the provided ID does not
 * match any records in the database.
 */
public class StudentNOtFoundException extends RuntimeException {

	/**
	 * Constructor a new StudentNOtFoundException with specified details message
	 * 
	 * @param message The message explain reason for this message
	 */
	public StudentNOtFoundException(String message) {
		super(message);
	}
}
