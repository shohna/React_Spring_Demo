package com.shohna.MyReactApp.services;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.shohna.MyReactApp.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	public String generateToken(Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis());
		
		Date expiryDate = new Date(now.getTime()+30_000);
		
		String userId = Long.toString(user.getId());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", (Long.toString(user.getId())));
		claims.put("username", user.getUsername());
		claims.put("fullName", user.getFullName());
		return Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, "SecretKeyToGenJWTs")
				.compact();
	}
}
