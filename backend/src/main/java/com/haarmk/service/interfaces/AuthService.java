package com.haarmk.service.interfaces;

public interface AuthService {
	void requestPasswordReset(String email);
	void resetPassword(String token, String newPassword);
	void verifyEmail(String token);
	void verifyEmailRequest(String email);
}
