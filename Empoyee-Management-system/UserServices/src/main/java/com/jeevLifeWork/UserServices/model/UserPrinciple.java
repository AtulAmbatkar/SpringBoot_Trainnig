package com.jeevLifeWork.UserServices.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Represents the principal user details used for authentication and
 * authorization.
 * <p>
 * The {@link UserPrinciple} class implements {@link UserDetails} to provide
 * user-specific information to Spring Security. This class is used to
 * encapsulate user details and authorities.
 * </p>
 */
public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Set<SimpleGrantedAuthority> authorities;

	/**
	 * Constructs a {@link UserPrinciple} from a {@link User} entity.
	 * <p>
	 * This constructor initializes the principal's username, password, and
	 * authorities from the provided {@link User} object.
	 * </p>
	 * 
	 * @param user the {@link User} object containing user details
	 */
	public UserPrinciple(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	/**
	 * Returns the authorities granted to the user.
	 * <p>
	 * In this implementation, the user is granted the role of "ROLE_USER".
	 * </p>
	 * 
	 * @return a collection of granted authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	/**
	 * Returns the password used to authenticate the user.
	 * 
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return this.password;
	}

	/**
	 * Returns the username used to authenticate the user.
	 * 
	 * @return the username
	 */
	@Override
	public String getUsername() {
		return this.username;
	}

	// Additional methods from UserDetails interface

	/**
	 * Indicates whether the user account has expired.
	 * <p>
	 * In this implementation, the account is assumed to be non-expired.
	 * </p>
	 * 
	 * @return {@code true} if the user account is non-expired; {@code false}
	 *         otherwise
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user account is locked.
	 * <p>
	 * In this implementation, the account is assumed to be non-locked.
	 * </p>
	 * 
	 * @return {@code true} if the user account is non-locked; {@code false}
	 *         otherwise
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indicates whether the user credentials (password) have expired.
	 * <p>
	 * In this implementation, the credentials are assumed to be non-expired.
	 * </p>
	 * 
	 * @return {@code true} if the user credentials are non-expired; {@code false}
	 *         otherwise
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is enabled.
	 * <p>
	 * In this implementation, the user is assumed to be enabled.
	 * </p>
	 * 
	 * @return {@code true} if the user is enabled; {@code false} otherwise
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
