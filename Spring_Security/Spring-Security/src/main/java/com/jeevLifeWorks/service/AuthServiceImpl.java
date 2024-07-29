package com.jeevLifeWorks.service;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeevLifeWorks.dto.SignupRequest;
import com.jeevLifeWorks.model.Users;
import com.jeevLifeWorks.repository.UserRepository;

/**
 * Implementation of the {@link IAuthService} interface. Provides user
 * authentication services such as creating a new user.
 */
@Service
public class AuthServiceImpl implements IAuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	// Constructor for dependency injection.
	@Autowired
	public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// Creates a new user with the details provided in the signup request.
	@Override
	public boolean createUser(SignupRequest signupRequest) {

		// Check if the user already exists
		System.out.println("signupRequest : " + signupRequest);
		if (userRepository.existByUsername(signupRequest.getUsername())) {
			return false;
		}

		// Create a new Users entity
		Users user = new Users();
		BeanUtils.copyProperties(signupRequest, user);

		// Hash the password
		String hasshPassword = passwordEncoder.encode(signupRequest.getPassword());

		user.setPassword(hasshPassword);
		user.setUsersname(signupRequest.getUsername());

		// Save the user to the repository
		userRepository.save(user);

		return true;
	}

}
