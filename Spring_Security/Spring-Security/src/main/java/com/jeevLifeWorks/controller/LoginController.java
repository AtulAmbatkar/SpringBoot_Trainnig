package com.jeevLifeWorks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeevLifeWorks.dto.LoginRequest;
import com.jeevLifeWorks.dto.LoginResponse;
import com.jeevLifeWorks.exception.UserNotFoundException;
import com.jeevLifeWorks.service.jwt.UserServiceImple;
import com.jeevLifeWorks.utils.JwtUtil;

/**
 * Controller for handling login requests.
 */
@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServiceImple userService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
		System.out.println("Authentication process start..........");
		System.out.println(" request : " + request.getUsername() + " password: " + request.getPassword());

		String username = request.getUsername();
		String password = request.getPassword();

		try {
			// Authenticate the user with the provided username and password
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (AuthenticationException e) {
			// Log the exception and return an unauthorized error response
			System.err.println("Authentication failed for user: " + username + ". Error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user");
		}

		UserDetails userDetails;
		try {
			// Load user details from the database
			userDetails = userService.loadUserByUsername(username);
		} catch (UsernameNotFoundException | UserNotFoundException e) {
			// Log the exception and return a not found error response
			System.err.println("User not found: " + username + ". Error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
		}

		// Generate JWT token for the authenticated user
		String token = jwtUtil.generateToken(userDetails.getUsername());

		// Return the token in the response
		System.out.println("token: " + token);
		return ResponseEntity.ok(new LoginResponse(token));
	}
}
