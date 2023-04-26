/**
 * 
 */
package com.haarmk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haarmk.model.Token;

/**
 * @author HMK05
 *
 */
@Repository
public interface TokenRepo extends JpaRepository<Token, Integer>{
	Optional<Token> findByToken(String token);
}
