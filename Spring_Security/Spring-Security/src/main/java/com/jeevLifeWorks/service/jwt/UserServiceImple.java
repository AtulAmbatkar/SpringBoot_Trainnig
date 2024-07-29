package com.jeevLifeWorks.service.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jeevLifeWorks.model.Users;
import com.jeevLifeWorks.repository.UserRepository;

/**
 * Implementation of {@link UserDetailsService} for loading user-specific data.
 */
@Service
public class UserServiceImple implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// Loads user-specific data by username for authentication.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			System.out.println("UserServiceImple.loadUserByUsername(): " + username);

			Users user = userRepository.findByUsername(username);
			System.out.println("user: " + user);

			if (user == null) {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}

			return new User(user.getUsername(), user.getPassword(), Collections.emptyList());

		} catch (Exception e) {
			System.err.println("Exception in loadUserByUsername: " + e.getMessage());
			throw e;
		}
	}
}
