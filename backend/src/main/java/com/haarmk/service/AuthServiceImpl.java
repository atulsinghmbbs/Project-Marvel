/**
 * 
 */
package com.haarmk.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haarmk.dto.EmailDetails;
import com.haarmk.exception.TokenException;
import com.haarmk.model.Token;
import com.haarmk.model.User;
import com.haarmk.service.interfaces.AuthService;
import com.haarmk.service.interfaces.EmailService;
import com.haarmk.service.interfaces.TokenService;
import com.haarmk.service.interfaces.UserService;
import com.haarmk.util.JwtUtil;

import io.jsonwebtoken.Claims;

/**
 * @author Ankit Patel
 *
 */
@Service
public class AuthServiceImpl implements AuthService {
	
    
	@Autowired private UserService userService;
    @Autowired private EmailService emailService;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private TokenService tokenService;
    
	public void requestPasswordReset(String email) {
		
		userService.getUserByEmail(email);
		Map<String, String> claims = new HashMap<>();
		claims.put("email_reset_password", email);
		String jwt = jwtUtil.generateToken(claims, 1000*60*10);
		Token token = tokenService.addToken(Token.builder().token(jwt).build());
		String[] recipients = {email};
		EmailDetails emailDetails = EmailDetails.builder()
				.recipients(recipients)
				.subject("HAARMK Rest password")
				.isHtml(Boolean.TRUE)
				.msgBody("<p>Please click the <a href=\"http://localhost:3000/reset-password?token="+token.getToken()+"\">link</a> to reset you password.</p>")
				.build();
				
		emailService.sendMail(emailDetails);
		
		
		
	}
	@Override
	public void resetPassword(String token, String newPassword) {
		Claims claims = jwtUtil.validateToken(token);
		try {
			tokenService.getToken(token);
			tokenService.deleteToken(token);
		} catch (TokenException e) {
			throw new TokenException("This token has been used.");
		}
		String email = claims.get("email_reset_password", String.class);
		if(email == null) {
			throw new TokenException("Not a valid token");
		}
		User foundUser = userService.getUserByEmail(email);
		foundUser.setPassword(passwordEncoder.encode(newPassword));
		userService.updateUser(foundUser);
		
	}
	

	
	@Override
	public void verifyEmailRequest(String email) {
		userService.getUserByEmail(email);
		Map<String, String> claims = new HashMap<>();
		claims.put("email_verification", email);
		String jwt = jwtUtil.generateToken(claims, 1000*60*1000);
		Token token = tokenService.addToken(Token.builder().token(jwt).build());
		String[] recipients = {email};
		EmailDetails emailDetails = EmailDetails.builder()
				.recipients(recipients)
				.subject("HAARMK Rest password")
				.isHtml(Boolean.TRUE)
				.msgBody("<p>Please click the <a href=\"http://localhost:8888/statics/reset-password?token="+token.getToken()+"\">link</a> to verify your email.</p>")
				.build();
				
		emailService.sendMail(emailDetails);
	}
	
	
	@Override
	public void verifyEmail(String token) {
		Claims claims = jwtUtil.validateToken(token);
		try {
			tokenService.getToken(token);
			tokenService.deleteToken(token);
		} catch (TokenException e) {
			throw new TokenException("This token has been used.");
		}
		String email = claims.get("email_verification", String.class);
		if(email == null) {
			throw new TokenException("Not a valid token");
		}
		User foundUser = userService.getUserByEmail(email);
		foundUser.setEnabled(true);
		userService.updateUser(foundUser);		
	}
	
	
	
	
	
	
}
