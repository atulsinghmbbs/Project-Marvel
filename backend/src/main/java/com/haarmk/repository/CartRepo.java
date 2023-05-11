/**
 * 
 */
package com.haarmk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haarmk.model.Cart;
import com.haarmk.model.Product;


public interface CartRepo extends JpaRepository<Cart, Integer> {
	
}
