/**
 * 
 */
package com.haarmk.service;

import java.util.Optional;

import org.aspectj.weaver.bcel.AtAjAttributes;
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
		Optional<Authority> authority2 = authorityRepo.findByAuthority(authority);
		if(authority2.isEmpty() && authority.equals("ROLE_user")) {
			Authority newAuthority = new Authority();
			newAuthority.setAuthority(authority);
			return addAuthority(newAuthority);
		}else if(authority2.isEmpty()){
			 throw new RuntimeException("No record found for authority: "+authority);
		}
		return authority2.get();
	}

	@Override
	public Authority addAuthority(Authority authority) {
		return authorityRepo.save(authority);
		
	}


}
