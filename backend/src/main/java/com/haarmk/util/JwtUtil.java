/**
 * 
 */
package com.haarmk.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.haarmk.dto.TokenDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * @author Ankit Patel
 *
 */

@Component
public class JwtUtil {
	
	@Value("${JWT_KEY}")
	private String key;
	
    @Value("${JWT_ACCESS_TOKEN_TIME}")
    Long jwtAccessTokenTime;
    
    @Value("${JWT_VERIFICATION_TOKEN_TIME}")
    Long jwtVerificationTime;
    
    @Value("${JWT_REFRESH_TOKEN_TIME}")
    Long jwtRefreshTokenTime;
    
	
	public Claims validateToken(String jwt) {
		
		SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
			
		
	}
	
	public Boolean isTokenVAlid(String token) {
		
		try {
			SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes());
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
			return true;
		} catch (Exception  e) { 
			return false;
		}
		
	}
	
	public TokenDto generateAccessToken(Map<String, String> claims) {
		Date now = new Date();
        Long duration = now.getTime() + jwtAccessTokenTime;
        Date expiryDate = new Date(duration);
        String token = generateToken(claims, jwtAccessTokenTime);
		return new TokenDto(TokenDto.TokenType.ACCESS, token, duration, OffsetDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));
	}
	
	public TokenDto generateRefreshToken(Map<String, String> claims) {
		Date now = new Date();
        Long duration = now.getTime() + jwtRefreshTokenTime;
        Date expiryDate = new Date(duration);
        String token = generateToken(claims, jwtRefreshTokenTime);
		return new TokenDto(TokenDto.TokenType.ACCESS, token, duration, OffsetDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));


	}
	
	
	public String generateToken(Map<String, String> claims, Long expirationTime) {
		Date now = new Date();
        Long duration = now.getTime() + expirationTime;
        Date expiryDate = new Date(duration);
        
		SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes());
		JwtBuilder jwts =  Jwts.builder()
 		.setIssuer("haarmk")
 		.setSubject("JWT Token")
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(key);
		if(claims != null) {
			for(Map.Entry<String,String> entry : claims.entrySet()) {
				jwts.claim(entry.getKey(), entry.getValue());
				
			}
		}
		String jwt = jwts.compact();
		return jwt;

	}
	
	
	
	public String createToken(Authentication authentication) {
		Map<String, String> claims = new HashMap<>();
		claims.put("username",authentication.getName());
		claims.put("authorities",populateAuthorities(authentication.getAuthorities()));
		return generateToken(claims, jwtVerificationTime);
	}
	
//	public String createToken(String username, Long timeDelta) {
//		SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes());
//		JwtBuilder jwts =  Jwts.builder()
//				.setIssuer("haarmk")
//				.setSubject(username)
//				.setIssuedAt(new Date())
//				.setExpiration(new Date(new Date().getTime()+ timeDelta))
//				.signWith(key);
//		return jwts.compact();
//	}

	public String getUsernameFromToken(String token) {
		SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}
	
    public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        
    	Set<String> authoritiesSet = new HashSet<>();
        
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
   
    
    }
	
	
}
