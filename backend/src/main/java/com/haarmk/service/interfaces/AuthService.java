package com.haarmk.service.interfaces;

public interface AuthService {
	void requestPasswordReset(String email);
	void resetPassword(String token, String newPassword);
	void varifyEmailRequest(String email);
	void varifyEmail(String token);
}
