package com.jeevLifeWorks.service;

import com.jeevLifeWorks.dto.SignupRequest;

/**
 * Service interface for user authentication and management.
 */
public interface IAuthService {

	// Creates a new user based on the provided signup request.
	boolean createUser(SignupRequest signupRequest);

}
