/**
 * 
 */
package com.haarmk.service.interfaces;

import org.springframework.stereotype.Service;

import com.haarmk.model.Authority;

/**
 * @author HMK05
 *
 */

@Service
public interface AuthorityService {
	Authority getAuthorityByAuthorityName(String authority);
	Authority addAuthority(Authority authority);
	
}
