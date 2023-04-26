package com.haarmk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.exception.HaarmkException;
import com.haarmk.model.ProductCategory;
import com.haarmk.repository.ProductCategoryRepo;
import com.haarmk.service.interfaces.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	
	@Autowired private ProductCategoryRepo productCategoryRepo;
	
	@Override
	public ProductCategory addProductCategory(ProductCategory productCategory) {
		if(productCategory == null) {
			throw new IllegalArgumentException("product category should not be null.");
		}
		return productCategoryRepo.save(productCategory);
	}

	@Override
	public ProductCategory updateProductCategory(ProductCategory productCategory) {
		productCategoryRepo.findById(productCategory.getId()).orElseThrow(() -> new HaarmkException("No product category found by id: "+productCategory.getId()));
		return productCategoryRepo.save(productCategory);
	}

	@Override
	public ProductCategory deleteProductCategory(Integer categoryId) {
		ProductCategory productCategory = productCategoryRepo.findById(categoryId).orElseThrow(() -> new HaarmkException("No product category found by id: "+categoryId));
		productCategoryRepo.delete(productCategory);
		return productCategory;
	}

	@Override
	public ProductCategory getProductCategoryByName(String name) {
		
		return productCategoryRepo.findByName(name).orElseThrow(() -> new HaarmkException("No product category found by id: "+name));
	}

	@Override
	public List<ProductCategory> getAllProductCategory() {
		List<ProductCategory> productCategories = productCategoryRepo.findAll();
		if(productCategories.isEmpty()) {
			throw new HaarmkException("No record found for product category");
		}
		return productCategories;
	}

	@Override
	public ProductCategory getProductCategoryById(Integer categoryId) {
		return productCategoryRepo.findById(categoryId).orElseThrow(() -> new HaarmkException("No product category found by id: "+categoryId));
	}

}
