package com.jeevLifeWorks.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

/**
 * Utility class for managing JSON Web Tokens (JWTs). Provides methods for
 * generating, validating, and extracting information from JWTs.
 */
@Component
public class JwtUtil {

	// Secret key used for signing JWTs.
	private String secret = "example";

	// Secret key object used for signing JWTs
	private SecretKey secretKey;

	// Initializes the secret key for signing JWTs.
	@PostConstruct
	public void init() {
		// Generate a secure random key suitable for HMAC-SHA512 algorithm
		this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}

	// Generates a JWT for the given username
	public String generateToken(String username) {
		System.out.println("JwtUtil.generateToken()");
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

	// Creates a JWT with the specified claims and subject.
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)).signWith(secretKey).compact();
	}

	// Validates the given JWT using the specified user details.
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// Extracts the username from the given JWT.
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// Extracts the expiration date from the given JWT.
	public Date extractExpiration(String token) {
		System.out.println("JwtUtil.extractExpiration()" + token);
		return extractClaim(token, Claims::getExpiration);
	}

	// Extracts a specific claim from the given JWT.
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// Extracts all claims from the given JWT.
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// Checks if the given JWT has expired.
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
}
