package com.jeevLifeWorks.UserServices.dto;

/**
 * Data Transfer Object (DTO) for encapsulating signup request details.
 */
public class SignupRequest {

	private String username;
	private String password;

	public SignupRequest() {
		super();
	}

	public SignupRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	// setters and getters
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