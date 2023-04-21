/**
 * 
 */
package com.haarmk.util;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * @author Ankit Patel
 *
 */

@Component
public class JwtUtil {
	
	@Autowired private Environment environment;
	
	@Value("${JWT_KEY}")
	private String key;
	
	
	public Claims validateToken(String jwt) {
		SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

	}
	
	public String generateToken(Map<String, String> claims, Integer expirationTime) {
		SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes());
		JwtBuilder jwts =  Jwts.builder()
 		.setIssuer("haarmk")
 		.setSubject("JWT Token")
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()+ expirationTime))
        .signWith(key);
		if(claims != null) {
			for(Map.Entry<String,String> entry : claims.entrySet()) {
				jwts.claim(entry.getKey(), entry.getValue());
				
			}
		}

		return jwts.compact();

	}
}
