package com.haarmk.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.haarmk.dto.LoginDto;
import com.haarmk.dto.LoginRequest;
import com.haarmk.dto.LoginResDto;
import com.haarmk.dto.LoginResponse;
import com.haarmk.dto.RefreshTokenDto;
import com.haarmk.dto.SignupDto;
import com.haarmk.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

public interface AuthService {
	void requestPasswordReset(String email);
	void resetPassword(String token, String newPassword);
	void verifyEmail(String token);
	void verifyEmailRequest(String email);
	ResponseEntity<LoginResDto> login(LoginDto loginDto);
	User signup(SignupDto signupDto);
	LoginResDto refreshToken(RefreshTokenDto refreshTokenDto);
//	User registerUser(User user);
//	ResponseEntity<LoginResponse> loginRef(@Valid LoginDto loginDto, String decryptedAccessToken,
//			String decryptedRefreshToken);
	ResponseEntity<LoginResponse> refresh(String decryptedRefreshToken);
	ResponseEntity<LoginResponse> getAccessToken(LoginResDto loginResDto);
	ResponseEntity<Void> logout(HttpServletRequest httppHttpServletRequest, HttpServletResponse httpServletResponse);
	
}
