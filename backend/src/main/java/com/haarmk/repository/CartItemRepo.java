package com.haarmk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haarmk.model.CartItem;
import com.haarmk.model.Product;

public interface CartItemRepo extends JpaRepository<CartItem, Long>{
	
	@Query("select ci.product.uniqueName from CartItem ci where ci.cart.id=:cartId and ci.product.uniqueName=:uniqueName")
	Optional<String> isProductPresentInCart(Integer cartId, String uniqueName);
}
