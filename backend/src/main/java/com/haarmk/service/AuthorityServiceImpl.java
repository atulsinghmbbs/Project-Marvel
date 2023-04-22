/**
 * 
 */
package com.haarmk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.model.Authority;
import com.haarmk.repository.AuthorityRepo;
import com.haarmk.service.interfaces.AuthorityService;

/**
 * @author HMK05
 *
 */

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired private AuthorityRepo authorityRepo;
	
	@Override
	public Authority getAuthorityByAuthorityName(String authority) {
		return authorityRepo.findByAuthority(authority).orElseThrow(()->new RuntimeException("No record found for authority: "+authority));
	}


}
