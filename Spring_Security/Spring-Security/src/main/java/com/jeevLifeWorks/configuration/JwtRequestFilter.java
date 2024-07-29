package com.jeevLifeWorks.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jeevLifeWorks.service.jwt.UserServiceImple;
import com.jeevLifeWorks.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter for validating JWT tokens and setting up Spring Security context.
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private final UserServiceImple userService;
	private final JwtUtil jwtUtil;

	@Autowired
	public JwtRequestFilter(UserServiceImple userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("filter start...");
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		System.out.println("authHeader: " + authHeader);
		// Check if the Authorization header is present and starts with "Bearer "
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7); // Extract the token
			username = jwtUtil.extractUsername(token); // Extract username from the token
		}

		// If a username is extracted and no authentication set
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.loadUserByUsername(username); // Load user details

			// Validate the token and set the authentication if valid
			if (jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		// Pass the request and response to the next filter in the chain
		filterChain.doFilter(request, response);
	}
}
