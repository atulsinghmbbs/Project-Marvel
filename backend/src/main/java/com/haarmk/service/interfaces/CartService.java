package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.exception.ProductException;
import com.haarmk.model.Product;

public interface CartService {
	
	List<Product> getAllProducts(Integer userID) throws ProductException;
	
	Product removeProductByProductId(Integer productId,Integer userID) throws ProductException;

	Product addProduct(Integer productId) throws ProductException;

	
	
}
