package com.haarmk.controller;


import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.dto.LoginDto;
import com.haarmk.dto.LoginResDto;
import com.haarmk.dto.LoginResponse;
import com.haarmk.dto.OperationStatusDto;
import com.haarmk.dto.PasswordResetReq;
import com.haarmk.dto.PasswordResetRequestDto;
import com.haarmk.dto.SignupDto;
import com.haarmk.model.Authority;
import com.haarmk.model.User;
import com.haarmk.service.interfaces.AuthService;
import com.haarmk.service.interfaces.AuthorityService;
import com.haarmk.util.SecurityCipher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
//	 	@Autowired private AuthenticationManager authenticationManager;
//	    @Autowired private PasswordEncoder passwordEncoder;
//	    @Autowired private UserService userService;
	    @Autowired private AuthService authService;
//	    @Autowired private JwtUtil jwtUtil;
	    @Autowired private AuthorityService authorityService;
//	    @Autowired private EmailService emailService;
//	    Integer timeDelta = 1000*60*60*24;


	    @PostMapping(value = "/add-authority")
	    public ResponseEntity<Authority> addAuthority(@Valid @RequestBody Authority authority) {
	    	System.out.println(OffsetDateTime.now());
			return new ResponseEntity<Authority>(authorityService.addAuthority(authority),HttpStatus.OK);
		}
	    
//	    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
	    @PostMapping(value = "/login")
	    public ResponseEntity<LoginResDto> login(@RequestBody LoginDto loginDto){
	    	return authService.login(loginDto);
//	        return new ResponseEntity<LoginResDto>(loginResDto,HttpStatus.OK);
	    }
	    
	    @PostMapping(value = "/logout")
	    public ResponseEntity<Void> logout(HttpServletRequest httppHttpServletRequest, HttpServletResponse httpServletResponse){
	    	return authService.logout(httppHttpServletRequest, httpServletResponse);
//	        return new ResponseEntity<LoginResDto>(loginResDto,HttpStatus.OK);
	    }
	    
	    

	    
	    
	    
	    
	    
	    
//	    @PostMapping(value = "/refresh-token")
//	    public ResponseEntity<LoginResDto> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto){
//    		LoginResDto loginResDto = authService.refreshToken(refreshTokenDto);	    	
//	        return new ResponseEntity<LoginResDto>(loginResDto,HttpStatus.OK);
//	       
//	    }
	    
	    
	    @PostMapping(value = "/signup")
	    public ResponseEntity<User> signup(@RequestBody SignupDto signupDto){
//	    	Authority authority = authorityService.getAuthorityByAuthorityName("ROLE_user");
//	    	
//	    	Long next_id = userService.getAutoIncrementValue();
//	    	User user = User.builder()
//	    			.firstName(signupDto.getFirstName())
//	    			.lastName(signupDto.getLastName())
//	    			.email(signupDto.getEmail())
//	    			.username("HIPL"+(next_id+1))
//	    			.password(signupDto.getPassword())
//	    			.enabled(false)
//	    			.build();
//	    	user.getAuthorities().add(authority);
//	    	user.getCart().setUser(user);
//	    	if(user.getPassword()!=null) {
//	    		user.setPassword(passwordEncoder.encode(user.getPassword()));
//	    	}
	    	
	    	User user = authService.signup(signupDto);
	    	return new ResponseEntity<User>(user,HttpStatus.CREATED);
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
	    
	    
	    
//	    refresh 
	    
//	    @PostMapping(value = "/login-refresh")
//	    public ResponseEntity<LoginResponse> login(
//	            @CookieValue(name = "accessToken", required = false) String accessToken,
//	            @CookieValue(name = "refreshToken", required = false) String refreshToken,
//	            @Valid @RequestBody LoginDto loginRequest
//	    ) {
////	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
////	        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
//	        String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
//	        return authService.loginRef(loginRequest, decryptedAccessToken, decryptedRefreshToken);
//	    }
//
	    @GetMapping(value = "/refresh-token")
	    public ResponseEntity<LoginResponse> refreshToken(@CookieValue(required = false) String refreshToken) {
	    	System.out.println("refresh token : "+refreshToken);
	         String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
	         return authService.refresh(decryptedRefreshToken);
	            
	    }
	    
	    @PostMapping(value = "/oauth2-access-token")
	    public ResponseEntity<LoginResponse> refreshToken(@RequestBody LoginResDto loginResDto) {
	    	return authService.getAccessToken(loginResDto);
	    	    
	    }
}
