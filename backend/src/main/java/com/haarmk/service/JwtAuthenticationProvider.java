package com.haarmk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haarmk.model.User;
import com.haarmk.service.interfaces.UserService;

@Service
public class JwtAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println(userService+"user service");
		User user = userService.getUserByUsername(authentication.getName());
		if(passwordEncoder.matches((String)authentication.getCredentials(), user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(user.getEmail(),null,user.getAuthorities());
		}else {
			throw new BadCredentialsException("invalid password");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
