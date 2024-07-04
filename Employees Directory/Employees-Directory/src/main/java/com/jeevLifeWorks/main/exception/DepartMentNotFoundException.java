package com.jeevLifeWorks.main.exception;

public class DepartMentNotFoundException extends RuntimeException{

	/**
	 * Constructor a new DepartMentNotFoundException with specified details message
	 * 
	 * @param message the details message explaining reason for the message.
	 */
	public DepartMentNotFoundException(String message) {
		super(message);
	}
}
