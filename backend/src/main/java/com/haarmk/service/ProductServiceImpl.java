package com.haarmk.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.exception.HaarmkException;
import com.haarmk.exception.ProductException;
import com.haarmk.model.Category;
import com.haarmk.model.Product;
import com.haarmk.repository.ProductRepo;
import com.haarmk.service.interfaces.CategoryService;
import com.haarmk.service.interfaces.ProductService;
import com.haarmk.util.Utils;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired ProductRepo productRepo;
	@Autowired CategoryService categoryService;
	
	
	@Override
	public Product addProduct(Product product) {
		
		if(product == null) throw new IllegalArgumentException("product should not be null");
		Category category = categoryService.getCategoryById(product.getCategory().getId());
		product.setCategory(category);
		System.out.println(product);
		return productRepo.save(product);
	}

	@Override
	public Product getProductById(Integer productId) {
		
		return productRepo.findById(productId).orElseThrow(()->new HaarmkException("No product found by Id: "+productId));
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> products = productRepo.findAll();
//		System.out.println(products.get(0).getMaps());
		if(products.isEmpty()) throw new HaarmkException("No record found in the database");
		return products;
	}

	@Override
	public Product updateProduct(Product product) {
		if(product == null) throw new IllegalArgumentException("product should not be null");
		return productRepo.save(product);
	}

	@Override
	public Product deleteProduct(Integer productId) {
		Product foundProduct = getProductById(productId);
		productRepo.delete(foundProduct);
		return foundProduct;
	}

	@Override
	public Product getProductByUniqueName(String name) {
		
		return productRepo.findByUniqueName(name).orElseThrow(()->new HaarmkException("No product found by Id: "+name));
	}

	@Override
	public Product getPeoductByUniqueNameAndCategory(String uniqueName, String categoryName) {
		
		return productRepo.getPeoductByUniqueNameAndCategory(uniqueName, categoryName).orElseThrow(()->new ProductException("No product found with product unique name: "+uniqueName+" and category name: "+categoryName));
	}

	@Override
	public Set<Product> getPeoductByCategoryName(String categoryName) {
		Set<Product> products = productRepo.getPeoductByCategoryName(categoryName);
		if(products.isEmpty()) throw new HaarmkException("no product found in cotegory: "+categoryName);
		return products;
	}

}
