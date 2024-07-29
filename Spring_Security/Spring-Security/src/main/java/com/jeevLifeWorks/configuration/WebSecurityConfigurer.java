package com.jeevLifeWorks.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;

/**
 * Configuration class for setting up Spring Security.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfigurer {

	@Autowired
	private JwtRequestFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable() // Disable CSRF for stateless API
				.authorizeHttpRequests().requestMatchers("/signup", "/login").permitAll() // Allow unauthenticated
																							// access to signup and
																							// login endpoints
				.and().authorizeHttpRequests().requestMatchers("/api/**") // Require authentication for /hello endpoint
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

//	Provides a BCryptPasswordEncoder bean for encoding passwords.
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

//	Provides an AuthenticationManager bean for managing authentication.
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
