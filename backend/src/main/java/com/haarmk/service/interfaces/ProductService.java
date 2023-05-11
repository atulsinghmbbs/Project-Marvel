package com.haarmk.service.interfaces;

import java.util.List;
import java.util.Set;

import com.haarmk.model.Product;

public interface ProductService {
//	Product addProduct(ProductDto productDto);
	Product getProductById(Integer productId);
	Product getProductByUniqueName(String name);
	List<Product> getAllProduct();
	Product updateProduct(Product product);
	Product deleteProduct(Integer productId);
	Product getPeoductByUniqueNameAndCategory(String uniqueName, String categoryName);
	Set<Product> getPeoductByCategoryName(String categoryName);
	Product addProduct(Product product);
}
