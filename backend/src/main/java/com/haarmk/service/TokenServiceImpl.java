/**
 * 
 */
package com.haarmk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.exception.TokenException;
import com.haarmk.model.Token;
import com.haarmk.repository.TokenRepo;
import com.haarmk.service.interfaces.TokenService;

/**
 * @author HMK05
 *
 */
@Service
public class TokenServiceImpl implements TokenService{
	
	@Autowired TokenRepo tokenRepo;
	
	@Override
	public Token addToken(Token token) {
		return tokenRepo.save(token);
		 
	}
	
	@Override
	public Token getToken(String token) {
		Token foundToken = tokenRepo.findByToken(token).orElseThrow(()->new TokenException("Token does not exist"+token));
		return foundToken;
	}
	
	@Override
	public void deleteToken(String token) {
		Token foundToken = this.getToken(token);
		tokenRepo.delete(foundToken);
	}



}
