package com.jeevLifeWork.EmployeeServices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Security configuration class for the application.
 * <p>
 * This configuration class sets up the security settings for the application,
 * including HTTP security, CORS, and password encoding. It ensures that
 * endpoints are properly secured and specifies how authentication and
 * authorization are handled.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Configures HTTP security settings.
	 * <p>
	 * This method sets up security configurations such as CORS, CSRF, authorization
	 * rules, JWT filtering, and session management. It protects the /employees
	 * endpoints and allows all other requests. CSRF protection is disabled for
	 * stateless APIs.
	 * </p>
	 * 
	 * @param httpSecurity the HttpSecurity object to configure
	 * @return the configured SecurityFilterChain
	 * @throws Exception if there is an error during configuration
	 */
	@Bean
	SecurityFilterChain securityChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs

				.authorizeHttpRequests(auth -> auth.requestMatchers("/employees/**").authenticated() // Protect
																										// /employees
																										// endpoints
						.anyRequest().permitAll() // Allow all other requests
				).addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class) // Add JWT filter
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Stateless
																												// sessions

		return httpSecurity.build(); // Build the SecurityFilterChain
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
	 * Configures a {@link PasswordEncoder} bean.
	 * <p>
	 * This method provides a {@link PasswordEncoder} for encoding passwords. The
	 * default encoder is a delegating encoder that supports multiple encoding
	 * formats.
	 * </p>
	 * 
	 * @return a PasswordEncoder instance
	 */

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
