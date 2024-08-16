package com.jeevLifeWork.UserServices.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a user in the system.
 * <p>
 * The {@link User} class is mapped to the "Rest_User" table in the database. It
 * includes fields for the user's ID, username, password, and email address. It
 * provides getter and setter methods for these fields, along with a default and
 * parameterized constructor.
 * </p>
 */
@Entity
@Table(name = "Rest_User")
public class User {

	/**
	 * The unique identifier for the user.
	 * <p>
	 * This field is automatically generated with an identity strategy.
	 * </p>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * The username of the user.
	 * <p>
	 * This field has a maximum length of 30 characters and cannot be null.
	 * </p>
	 */
	@Column(length = 30, nullable = false)
	private String username;

	/**
	 * The password of the user.
	 * <p>
	 * This field has a maximum length of 100 characters and cannot be null.
	 * </p>
	 */
	@Column(length = 100, nullable = false)
	private String password;

	/**
	 * The email address of the user.
	 * <p>
	 * This field has a maximum length of 40 characters, must be unique, and cannot
	 * be null.
	 * </p>
	 */
	@Column(length = 40, unique = true, nullable = false)
	private String email;

	/**
	 * Default constructor.
	 * <p>
	 * Initializes a new instance of the {@link User} class.
	 * </p>
	 */
	public User() {
		super();
	}

	/**
	 * Parameterized constructor.
	 * <p>
	 * Initializes a new instance of the {@link User} class with the specified
	 * username, password, and email.
	 * </p>
	 * 
	 * @param username the username of the user
	 * @param password the password of the user
	 * @param email    the email address of the user
	 */
	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	/**
	 * Gets the unique identifier for the user.
	 * 
	 * @return the user's ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the unique identifier for the user.
	 * 
	 * @param id the user's ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the username of the user.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 * 
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password of the user.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the email address of the user.
	 * 
	 * @return the email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the user.
	 * 
	 * @param email the email address to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Provides a string representation of the {@link User} object.
	 * <p>
	 * The string includes the user's ID, username, password, and email.
	 * </p>
	 * 
	 * @return a string representation of the user
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
}
