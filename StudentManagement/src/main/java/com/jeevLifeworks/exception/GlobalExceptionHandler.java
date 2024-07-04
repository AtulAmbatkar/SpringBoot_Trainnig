package com.jeevLifeworks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * The GlobalExceptionHandler class handles Exceptions globally for the
 * Application. It provide different types of method to handle the exception and
 * return appropriate response.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles validation exceptions for method those @valid annotation
	 * 
	 * @param ex the MethodArgumentNotValidException thrown when method validation
	 *           is failed
	 * @return ResponseEntity containing a map the field and message
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/**
	 * The StudentNOtFoundException thrown when student not found
	 * 
	 * @param ex StudentNOtFoundException thrown when student are not present in
	 *           database
	 * @return ResponseEntity containing error message and BAD_REQUEST
	 */
	@ExceptionHandler(StudentNOtFoundException.class)
	public ResponseEntity<String> handleStudentNOtFoundException(StudentNOtFoundException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	/**
	 * The InvalidInputDataException thrown when data is add into database with
	 * invalid data
	 * 
	 * @param ex InvalidInputDataException thrown when student data invalid insert
	 * @return ResponseEntity containing error message and BAD_REQUEST
	 */
	@ExceptionHandler(InvalidInputDataException.class)
	public ResponseEntity<String> handleInvalidInputDataException(InvalidInputDataException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
