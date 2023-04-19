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
import com.haarmk.model.User;
import com.haarmk.service.interfaces.AuthService;
import com.haarmk.service.interfaces.EmailService;
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
    
	public void requestPasswordReset(String email) {
		
		User foundUser = userService.getUserByEmail(email);
		Map<String, String> claims = new HashMap<>();
		claims.put("email", email);
		String jwt = jwtUtil.generateToken(null, 10000);
		System.out.println(jwt);
		String[] recipients = {email};
		EmailDetails emailDetails = EmailDetails.builder()
				.recipients(recipients)
				.subject("HAARMK Rest password")
				.isHtml(Boolean.TRUE)
				.msgBody("<p>Please click the <a href=\"http://localhost:8888/statics/reset-password?token="+jwt+"\">link</a> to reset you password.</p>")
				.build();
				
		emailService.sendMail(emailDetails);
		
		
		
	}
	@Override
	public void resetPassword(String token, String newPassword) {
		Claims claims = jwtUtil.validateToken(token);
		String email = claims.get("email", String.class);
		User foundUser = userService.getUserByEmail(email);
		foundUser.setPassword(passwordEncoder.encode(newPassword));
		userService.updateUser(foundUser);
		
	}
	

	
	@Override
	public void varifyEmailRequest(String email) {
		User foundUser = userService.getUserByEmail(email);
		Map<String, String> claims = new HashMap<>();
		claims.put("email", email);
		String jwt = jwtUtil.generateToken(null, 10000);
		System.out.println(jwt);
		String[] recipients = {email};
		EmailDetails emailDetails = EmailDetails.builder()
				.recipients(recipients)
				.subject("HAARMK Rest password")
				.isHtml(Boolean.TRUE)
				.msgBody("<p>Please click the <a href=\"http://localhost:8888/statics/reset-password?token="+jwt+"\">link</a> to reset you password.</p>")
				.build();
				
		emailService.sendMail(emailDetails);
	}
	
	
	@Override
	public void varifyEmail(String token) {
		Claims claims = jwtUtil.validateToken(token);
		String email = claims.get("email", String.class);
		User foundUser = userService.getUserByEmail(email);
		userService.updateUser(foundUser);		
	}
	
	
	
	
	
}
