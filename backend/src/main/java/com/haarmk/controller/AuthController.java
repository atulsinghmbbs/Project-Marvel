package com.haarmk.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.dto.LoginDto;
import com.haarmk.dto.LoginResDto;
import com.haarmk.dto.OperationStatusDto;
import com.haarmk.dto.PasswordResetReq;
import com.haarmk.dto.PasswordResetRequestDto;
import com.haarmk.model.User;
import com.haarmk.service.interfaces.AuthService;
import com.haarmk.service.interfaces.UserService;
import com.haarmk.util.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	 	@Autowired private AuthenticationManager authenticationManager;
	    @Autowired private PasswordEncoder passwordEncoder;
	    @Autowired private UserService userService;
	    @Autowired private AuthService authService;
	    @Autowired private JwtUtil jwtUtil;


	   
	    @PostMapping(value = "/login")
	    
	    public ResponseEntity<LoginResDto> login(@RequestBody LoginDto loginDto){
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
	        User user =  (User) authentication.getPrincipal();
	        Map<String, String> claims = new HashMap<>();
	        claims.put("username", user.getUsername());
	        claims.put("authorities", authentication.getAuthorities().stream().map((a)->a.getAuthority().toUpperCase()).collect(Collectors.joining(",")));
	        String jwt = jwtUtil.generateToken(claims, 80000);
    		LoginResDto loginResDto = new LoginResDto();
    		loginResDto.setToken(jwt);
    		loginResDto.setType("Authorization: Bearer");
	        return new ResponseEntity<LoginResDto>(loginResDto,HttpStatus.OK);
	       
	    }
	    
	    @PostMapping(value = "/signup")
	    public ResponseEntity<User> signup(@RequestBody User user){
	    	if(user.getPassword()!=null) {
	    		user.setPassword(passwordEncoder.encode(user.getPassword()));
	    	}
	    	return new ResponseEntity<User>(userService.addUser(user),HttpStatus.CREATED);
	    }
	    
	    @PostMapping(value = "/reset-password-request")
	    public ResponseEntity<OperationStatusDto> passwordResetRequest(@Valid @RequestBody PasswordResetRequestDto passwordResetRequestDto) {
	    	authService.requestPasswordReset(passwordResetRequestDto.getEmail());
			OperationStatusDto operationStatusDto = new OperationStatusDto();
			operationStatusDto.setOperation("Reset Password");
			operationStatusDto.setStatus("Success");
			return new ResponseEntity<OperationStatusDto>(operationStatusDto,HttpStatus.OK);
		}
	    
	    
	    @PostMapping(value = "/reset-password")
	    public ResponseEntity<OperationStatusDto> passwordReset(@Valid @RequestBody PasswordResetReq passwordResetReq) {
	    	authService.resetPassword(passwordResetReq.getToken(), passwordResetReq.getNewPassword());
			OperationStatusDto operationStatusDto = new OperationStatusDto();
			operationStatusDto.setOperation("Reset Password");
			operationStatusDto.setStatus("Success");
			return new ResponseEntity<OperationStatusDto>(operationStatusDto,HttpStatus.OK);
			
	    	
		}
	    
	    
	    @PostMapping(value = "/varify-email-request")
	    public ResponseEntity<OperationStatusDto> varifyEmailRequest(@Valid @RequestBody PasswordResetRequestDto passwordResetRequestDto) {
	    	authService.verifyEmailRequest(passwordResetRequestDto.getEmail());
			OperationStatusDto operationStatusDto = new OperationStatusDto();
			operationStatusDto.setOperation("Reset Password");
			operationStatusDto.setStatus("Success");
			return new ResponseEntity<OperationStatusDto>(operationStatusDto,HttpStatus.OK);
		}
	    
	    
	    @GetMapping(value = "/varify-email")
	    public ResponseEntity<OperationStatusDto>  varifyEmail(@RequestParam String token) {
	    	authService.verifyEmail(token);
			OperationStatusDto operationStatusDto = new OperationStatusDto();
			operationStatusDto.setOperation("Reset Password");
			operationStatusDto.setStatus("Success");
			return new ResponseEntity<OperationStatusDto>(operationStatusDto,HttpStatus.OK);
		}
	    
	    
	    
	    
}
