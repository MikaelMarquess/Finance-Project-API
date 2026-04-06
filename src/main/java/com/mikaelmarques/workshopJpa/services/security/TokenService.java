package com.mikaelmarques.workshopJpa.services.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	public String secret;
	
	public String generateToken(String email) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(email)
					.withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
					.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Error while generating token", e);
		}
	}
	
	public String validateToken(String token) {
		try {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.require(algorithm)
				.withIssuer("auth-api")
				.build()
				.verify(token)
				.getSubject();
		}catch(JWTVerificationException e) {
			return "";
		}
	}
	
}
