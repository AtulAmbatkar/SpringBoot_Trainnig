package com.jeevLifeWorks.UserServices.dto;

/**
 * Data Transfer Object (DTO) for encapsulating signup request details.
 */
public class SignupRequest {

	private String email;
	private String username;
	private String password;

	public SignupRequest() {
		super();
	}

	public SignupRequest(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	@Override
	public String toString() {
		return "SignupRequest [email=" + email + ", username=" + username + ", password=" + password + "]";
	}

	

}