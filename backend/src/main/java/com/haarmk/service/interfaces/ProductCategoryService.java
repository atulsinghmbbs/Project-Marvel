package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.model.ProductCategory;

public interface ProductCategoryService {
	ProductCategory addProductCategory(ProductCategory productCategory);
	ProductCategory updateProductCategory(ProductCategory productCategory);
	ProductCategory deleteProductCategory(Integer categoryId);
	ProductCategory getProductCategoryByName(String name);
	ProductCategory getProductCategoryById(Integer categoryId);
	List<ProductCategory> getAllProductCategory();
}
