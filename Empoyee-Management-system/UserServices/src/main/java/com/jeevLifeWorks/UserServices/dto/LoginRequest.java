package com.jeevLifeWorks.UserServices.dto;

/**
 * Data Transfer Object (DTO) for encapsulating login request details.
 */
public class LoginRequest {

	private String username;
	private String password;

	public LoginRequest() {
		super();
	}

	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// toString
	@Override
	public String toString() {
		return "SignupRequest [username=" + username + ", password=" + password + "]";
	}

}