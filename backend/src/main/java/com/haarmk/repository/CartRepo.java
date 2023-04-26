/**
 * 
 */
package com.haarmk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haarmk.model.Cart;


public interface CartRepo extends JpaRepository<Cart, Integer> {
	
}
