package com.jeevLifeWork.EmployeeServices.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;

/**
 * JWT filter for authentication.
 * <p>
 * This filter extracts the JWT from the request, validates it, and sets the
 * authentication context if the token is valid. It ensures that the user's
 * authentication details are set in the security context.
 * </p>
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

	/**
	 * Filters the request to check for a JWT and sets the authentication context.
	 * <p>
	 * This method extracts the JWT from the `Authorization` header of the request,
	 * validates it, and creates an authentication object if the token is valid. It
	 * sets the authentication object in the {@link SecurityContextHolder} for the
	 * duration of the request.
	 * </p>
	 * 
	 * @param request     the HTTP request
	 * @param response    the HTTP response
	 * @param filterChain the filter chain
	 * @throws ServletException if an error occurs during the filtering process
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			// Validate token here and set authentication context
			// This example assumes token contains username
			String username = getUsernameFromToken(token); // Implement token parsing
			if (username != null) {
				UserDetails userDetails = new User(username, "",
						Collections.singletonList(new SimpleGrantedAuthority("USER")));
				Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * Parses the JWT to extract the username.
	 * <p>
	 * This method contains the logic for parsing the JWT to retrieve the username.
	 * The actual implementation would involve using a JWT library to validate and
	 * parse the token.
	 * </p>
	 * 
	 * @param token the JWT
	 * @return the username extracted from the token, or null if the token is
	 *         invalid
	 */
	private String getUsernameFromToken(String token) {
		// Token parsing logic here (e.g., using JWT library)
		return "parsed-username"; // Placeholder
	}
}
