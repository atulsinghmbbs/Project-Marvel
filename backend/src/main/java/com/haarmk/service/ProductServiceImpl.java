package com.haarmk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.exception.HaarmkException;
import com.haarmk.model.Product;
import com.haarmk.model.ProductCategory;
import com.haarmk.model.ProductProperty;
import com.haarmk.repository.ProductRepo;
import com.haarmk.service.interfaces.ProductCategoryService;
import com.haarmk.service.interfaces.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired ProductRepo productRepo;
	@Autowired ProductCategoryService productCategoryService;
	
	@Override
	public Product addProduct(Product product) {
		if(product == null) throw new IllegalArgumentException("product should not be null");
		for(ProductProperty pp : product.getPropreties()) {
			pp.setProduct(product);
		}
		ProductCategory productCategory = productCategoryService.getProductCategoryById(product.getCategory().getId());
		product.setCategory(productCategory);
		return productRepo.save(product);
	}

	@Override
	public Product getProductById(Integer productId) {
		
		return productRepo.findById(productId).orElseThrow(()->new HaarmkException("No product found by Id: "+productId));
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> products = productRepo.findAll();
		if(products.isEmpty()) throw new HaarmkException("No record found in the database");
		return products;
	}

	@Override
	public Product updateProduct(Product product) {
		if(product == null) throw new IllegalArgumentException("product should not be null");
		for(ProductProperty pp : product.getPropreties()) {
			pp.setProduct(product);
		}
		ProductCategory productCategory = productCategoryService.getProductCategoryById(product.getCategory().getId());
		product.setCategory(productCategory);
		getProductById(product.getId());
		return productRepo.save(product);
	}

	@Override
	public Product deleteProduct(Integer productId) {
		Product foundProduct = getProductById(productId);
		productRepo.delete(foundProduct);
		return foundProduct;
	}

	@Override
	public Product getProductByName(String name) {
		
		return productRepo.findByName(name).orElseThrow(()->new HaarmkException("No product found by Id: "+name));
	}

}
