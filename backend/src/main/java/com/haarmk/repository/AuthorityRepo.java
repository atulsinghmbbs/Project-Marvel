/**
 * 
 */
package com.haarmk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Authority;

/**
 * @author HMK05
 *
 */
public interface AuthorityRepo extends JpaRepository<Authority, Integer>{
	Optional<Authority> findByAuthority(String authority);
}
