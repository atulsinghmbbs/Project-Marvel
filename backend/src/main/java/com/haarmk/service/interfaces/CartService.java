package com.haarmk.service.interfaces;

import com.haarmk.exception.ProductException;
import com.haarmk.model.Cart;

public interface CartService {
	
	Cart removeProduct(Integer productId, String username) throws ProductException;

	Cart addProduct(Integer productId,  String username) throws ProductException;
	
	Cart addDomainToCart(String domainName,  String username) throws ProductException;
	
	Cart getCart(String user);
	
	
}
