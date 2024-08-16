package com.jeevLifeWork.UserServices.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Service for handling JSON Web Tokens (JWTs). Provides functionality for
 * generating, validating, and extracting information from JWTs.
 */
@Component
public class JwtService {

	// secret key for signing the jwt
	private static final String SECRETKEY = "TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg=\r\n" + "";

	// generate jwt token for giving username
	public String generateToken(String username) {
		if (username != null) {
			// prepare claims for token
			Map<String, Object> claims = new HashMap<>();

			// Build jwt token with claims,subject,issued,expire token, and signing
			// algorithm
			// valid for 5 minute
			return Jwts.builder().setClaims(claims).setSubject(username)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
					.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

		}
		return "String is null " + username;
	}

	private Key getSignKey() {
		// Decode the base64 encoded secret key and return a Key object
		byte[] keysBytes = Decoders.BASE64.decode(SECRETKEY);
		return Keys.hmacShaKeyFor(keysBytes);
	}

	// Extract user name from the token
	public String extractUsername(String token) {
		// Extract and return username the subject claims
		return extractClaim(token, Claims::getSubject);
	}

	// Extract Date For Expiration in token
	public Date extractExiprationDate(String token) {

		return extractClaim(token, Claims::getExpiration);
	}

	// Extracting the specific claims fromjwt token
	// claimResolver function is extract Claims
	// return The value of the specified claim.
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllCliams(token);
		return claimResolver.apply(claims);
	}

	// Extracts all claims from the JWT token.
	// return Claims object containing all claims.
	private Claims extractAllCliams(String token) {

		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	// check the if jwt token is expired
	public Boolean isTokenExpired(String token) {
		// check token expiration time before current time
		return extractExiprationDate(token).before(new Date());
	}

	// Validate Jwt token againns the userDetails
	public Boolean validateToken(String token, UserDetails userDetails) {
		// Extract username from token and check if it matches UserDetails' username
		final String userName = extractUsername(token);
		// Also check if the token is expired
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
