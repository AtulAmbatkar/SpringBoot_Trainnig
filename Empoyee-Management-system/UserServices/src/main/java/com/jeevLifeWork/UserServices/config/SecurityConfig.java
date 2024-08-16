package com.jeevLifeWork.UserServices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jeevLifeWork.UserServices.services.UserService;

/**
 * Security configuration class for the User Services application.
 * <p>
 * This class configures security settings including HTTP request authorization,
 * session management, authentication, and password encoding. It also integrates
 * JWT-based authentication via a custom filter.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

	// UserDetailsService bean for userAuthentication
	@Bean
	public UserDetailsService userDetailsService() {

		return new UserService();
	}

	/**
	 * Configures the {@link SecurityFilterChain} for HTTP security.
	 * <p>
	 * This method sets up CSRF protection, request authorization rules, session
	 * management, and integrates a custom JWT filter for authentication. It
	 * requires authentication for all endpoints except the specified public
	 * endpoints for registration and login.
	 * </p>
	 * 
	 * @param httpSecurity the {@link HttpSecurity} object used for configuration
	 * @return the configured {@link SecurityFilterChain}
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	public SecurityFilterChain securityChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity.csrf(crsf -> crsf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/users/register", "/users/login").permitAll()
//						.requestMatchers("/users/profile") // Specify role-based access here
						.anyRequest().authenticated()) // Require authentication for all other requests
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())// Register the authentication provider
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add the JWT filter before
																						// processing the request
				.build();

	}

	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**") .allowedOrigins("http://localhost:4200") // Allow
	 * your frontend origin .allowedMethods("GET", "POST", "PUT", "DELETE",
	 * "OPTIONS") .allowedHeaders("*") .allowCredentials(true); } }; }
	 */

	/**
	 * Defines an {@link AuthenticationProvider} bean that uses
	 * {@link DaoAuthenticationProvider} to handle user authentication.
	 * <p>
	 * This provider integrates with {@link UserDetailsService} for loading user
	 * details and {@link PasswordEncoder} for password validation.
	 * </p>
	 * 
	 * @return a configured {@link DaoAuthenticationProvider} instance
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	/**
	 * Defines a {@link PasswordEncoder} bean to handle password encoding and
	 * verification.
	 * <p>
	 * This bean provides a delegating password encoder that supports multiple
	 * encoding formats.
	 * </p>
	 * 
	 * @return a {@link PasswordEncoder} instance
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * Defines an {@link AuthenticationManager} bean to manage authentication
	 * processes.
	 * <p>
	 * This bean is used by the security configuration to handle authentication
	 * requests.
	 * </p>
	 * 
	 * @param config the {@link AuthenticationConfiguration} used for configuration
	 * @return the {@link AuthenticationManager} instance
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
