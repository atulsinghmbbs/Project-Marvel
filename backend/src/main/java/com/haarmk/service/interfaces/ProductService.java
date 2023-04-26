package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.model.Product;

public interface ProductService {
	Product addProduct(Product product);
	Product getProductById(Integer productId);
	Product getProductByName(String name);
	List<Product> getAllProduct();
	Product updateProduct(Product product);
	Product deleteProduct(Integer productId);
}
