package com.haarmk.service.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import com.haarmk.exception.ProductException;
import com.haarmk.model.Cart;

public interface CartService {
	


	Cart addProduct(Integer productId, String username, JsonNode details) throws ProductException;
	
	Cart addDomainToCart(String domainName,  String username) throws ProductException;
	
	
	Cart getCart(String user);

	Cart removeProduct(Long cartItemId, String username) throws ProductException;
	
	Cart changeCartItemQty(Long cartItemId, Integer qty, String username) throws ProductException;
	
	void updateCartTotal(Cart cart);

	Cart clearCart(String username);

	Cart updateCartItemDetails(Long cartItemId, String username, JsonNode jsonNode);

	

	
	
	
}
