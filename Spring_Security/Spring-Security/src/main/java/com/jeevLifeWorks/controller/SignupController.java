package com.jeevLifeWorks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeevLifeWorks.dto.SignupRequest;
import com.jeevLifeWorks.service.IAuthService;

/**
 * Controller for handling user signup requests.
 */
@RestController
public class SignupController {

	private final IAuthService userService;

	@Autowired
	public SignupController(IAuthService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signupUser(@RequestBody SignupRequest signupRequest) {
		// Log the received signup request
		System.out.println("Received signup request: " + signupRequest);

		boolean isUserCreated;
		try {
			// Attempt to create a new user
			isUserCreated = userService.createUser(signupRequest);
		} catch (Exception e) {
			// Log the exception and return an error response
			System.err.println("Error occurred while creating user: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}

		// Return success or failure response based on the result
		if (isUserCreated) {
			return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
		}
	}
}
