package com.jeevLifeWorks.dto;

/**
 * Data Transfer Object (DTO) for encapsulating the login response data.
 */

public class LoginResponse {

	// for takon
	private String token;

	public LoginResponse(String token) {
		super();
		this.token = token;
	}

	// get token as response
	public String getToken() {
		return token;
	}
}
