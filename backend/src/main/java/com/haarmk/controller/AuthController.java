package com.haarmk.controller;

import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.dto.LoginDto;
import com.haarmk.dto.LoginResDto;
import com.haarmk.model.User;
import com.haarmk.service.interfaces.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/haarmk")
public class AuthController {
	 	@Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Autowired
	    private UserService userService;
	    @Autowired
	    private Environment environment;
	   
	    @PostMapping(value = "/login")
	    public ResponseEntity<LoginResDto> login(@RequestBody LoginDto loginDto){
	    	
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
	        SecretKey key = Keys.hmacShaKeyFor(environment.getProperty("JWT_KEY").getBytes());
	         
	        String jwt = Jwts.builder()
	        		.setIssuer("relevel")
	        		.setSubject("JWT Token")
	                .claim("username", authentication.getName())
	                .claim("authorities", authentication.getAuthorities().stream().map((a)->a.getAuthority().toUpperCase()).collect(Collectors.joining(",")))
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(new Date().getTime()+ 30000000)) // expiration time of 8 hours
	                .signWith(key).compact();
	        
	        		LoginResDto loginResDto = new LoginResDto();
	        		loginResDto.setToken(jwt);
	        return new ResponseEntity<LoginResDto>(loginResDto,HttpStatus.OK);
	       
	    }
	    
	    @PostMapping(value = "/signup")
	    public ResponseEntity<User> signup(@RequestBody User user){
	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
	    	
	    	
	    	
	    	return new ResponseEntity<User>(userService.addUser(user),HttpStatus.CREATED);
	    }
}
