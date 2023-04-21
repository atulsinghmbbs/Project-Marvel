/**
 * 
 */
package com.haarmk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Cart;

/**
 * @author Ankit Patel
 *
 */

public interface CartRepo extends JpaRepository<Cart, Integer> {
	
}
