package com.jeevLifeWork.UserServices.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeevLifeWork.UserServices.model.User;
import com.jeevLifeWork.UserServices.services.UserService;
import com.jeevLifeWorks.UserServices.dto.LoginRequest;
import com.jeevLifeWorks.UserServices.dto.LoginResponse;
import com.jeevLifeWorks.UserServices.dto.SignupRequest;

/**
 * UserController handles HTTP requests related to user operations including
 * registration, login, and profile retrieval.
 * <p>
 * This class exposes endpoints for user registration, login authentication, and
 * fetching the profile of the currently logged-in user. It integrates with
 * {@link UserService} for user management and {@link JwtService} for token
 * generation.
 * </p>
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private com.jeevLifeWork.UserServices.services.JwtService jwtService;

	/**
	 * Registers a new user.
	 * <p>
	 * This endpoint allows the registration of a new user by accepting user details
	 * in the request body. It invokes {@link UserService#addNewUser(User)} to save
	 * the user.
	 * </p>
	 * 
	 * @param user the {@link User} object containing user details
	 * @return a {@link ResponseEntity} with a message indicating the result of the
	 *         registration
	 */
	@PostMapping("/register")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
		System.out.println("user " + signupRequest);
		String response = userService.addNewUser(signupRequest);
		return ResponseEntity.ok(response);
	}

	/**
	 * Authenticates a user and returns a JWT.
	 * <p>
	 * This endpoint handles user login by authenticating the user with provided
	 * credentials. If successful, it generates and returns a JWT token using
	 * {@link JwtService#generateToken(String)}.
	 * </p>
	 * 
	 * @param request the {@link LoginRequest} object containing username and
	 *                password
	 * @return a {@link ResponseEntity} containing the {@link LoginResponse} with
	 *         the JWT token
	 * @throws UsernameNotFoundException if authentication fails
	 */
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
		// Authenticate user
		System.out.println("user :" + request);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		System.out.println("authenticationManager: " + authenticationManager);
		if (authentication.isAuthenticated()) {

			String token = jwtService.generateToken(request.getUsername());
			return ResponseEntity.ok(new LoginResponse(token));
		} else {
			throw new UsernameNotFoundException("Invalid user request");
		}
	}

	/**
	 * Retrieves the profile of the currently logged-in user.
	 * <p>
	 * This endpoint returns the profile information of the authenticated user. It
	 * uses {@link Principal} to get the username of the currently authenticated
	 * user and fetches user details from
	 * {@link UserService#findAnyUsername(String)}.
	 * </p>
	 * 
	 * @param principal contains the username of the authenticated user
	 * @return a {@link ResponseEntity} containing the {@link User} object with
	 *         profile details
	 */
	@GetMapping("/profile")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getProfile(Principal principal) {
		// Principal contains the username of the authenticated user
		User user = userService.findAnyUsername(principal.getName());
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
