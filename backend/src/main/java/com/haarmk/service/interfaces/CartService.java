package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.exception.ProductException;
import com.haarmk.model.Product;

public interface CartService {
	
	public Product addProduct(Product product,Integer userID) throws ProductException;
	
	public List<Product> getAllProducts(Integer userID) throws ProductException;
	
	public Product deleteProductByProductId(Integer productId,Integer userID) throws ProductException;

	
	
	
	
}
