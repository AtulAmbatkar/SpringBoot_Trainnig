package com.jeevLifeWork.UserServices.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeevLifeWork.UserServices.model.User;
import com.jeevLifeWork.UserServices.model.UserPrinciple;
import com.jeevLifeWork.UserServices.repository.IUserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private IUserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Loads user details by username for authentication.
	 *
	 * @param username the username of the user to be loaded
	 * @return UserDetails object containing user details
	 * @throws UsernameNotFoundException if the user is not found
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByUsername(username);
		return user.map(UserPrinciple::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

	/**
	 * Registers a new user by encoding their password and saving to the database.
	 *
	 * @param user the user object to be registered
	 * @return a success message with the user's ID
	 */
	public String addNewUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password
		int userId = repository.save(user).getId(); // Save user and get ID
		return "User registered successfully with ID: " + userId;
	}

	/**
	 * Finds a user by username.
	 *
	 * @param username the username of the user to be found
	 * @return the User object if found
	 * @throws UsernameNotFoundException if the user is not found
	 */
	public User findAnyUsername(String username) {
		Optional<User> optionUser = repository.findByUsername(username);
		return optionUser.orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));
	}
}
