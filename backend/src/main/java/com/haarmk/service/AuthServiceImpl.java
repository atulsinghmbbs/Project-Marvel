/**
 * 
 */
package com.haarmk.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.dto.EmailDetails;
import com.haarmk.model.User;
import com.haarmk.service.interfaces.AuthService;
import com.haarmk.service.interfaces.EmailService;
import com.haarmk.service.interfaces.UserService;
import com.haarmk.util.JwtUtil;

/**
 * @author Ankit Patel
 *
 */
@Service
public class AuthServiceImpl implements AuthService {
	
    
	@Autowired private UserService userService;
    @Autowired private EmailService emailService;
    @Autowired private JwtUtil jwtUtil;
	public void requestPasswordReset(String email) {
		
		User foundUser = userService.getUserByEmail(email);
		System.out.println(foundUser);
		Map<String, String> claims = new HashMap<>();
		claims.put("email", "haarmkinfotech@gmail.com");
		String jwt = jwtUtil.generateToken(null, 10000);
		System.out.println(jwt);
		String[] recipients = {"haarmkinfotech@gmail.com"};
		EmailDetails emailDetails = EmailDetails.builder()
				.recipients(recipients)
				.subject("HAARMK Rest password")
				.isHtml(Boolean.TRUE)
				.msgBody("<p>Please click the <a href=\"http://localhost:8888/statics/reset-password?token="+jwt+"\">link</a> to reset you password.</p>")
				.build();
				;
		emailService.sendMail(emailDetails);
		
		
		
	}
}
