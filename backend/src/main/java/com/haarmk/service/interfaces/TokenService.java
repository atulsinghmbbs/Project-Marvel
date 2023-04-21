package com.haarmk.service.interfaces;

import com.haarmk.model.Token;

public interface TokenService {
	Token addToken(Token token);
	Token getToken(String token);
	void deleteToken(String token);
}
