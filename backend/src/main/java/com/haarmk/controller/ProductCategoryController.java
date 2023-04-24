package com.haarmk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.exception.FeedbackException;
import com.haarmk.model.ProductCategory;
import com.haarmk.service.interfaces.ProductCategoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/product-categories")
public class ProductCategoryController {
	@Autowired ProductCategoryService productCategoryService;
	
	
	@PostMapping("/")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<ProductCategory> addProductCategory(@Valid  @RequestBody ProductCategory productCategory){
		ProductCategory createdProductCategory = productCategoryService.addProductCategory(productCategory);
		
		return new ResponseEntity<ProductCategory>(createdProductCategory , HttpStatus.CREATED);
	}
	


	
	@GetMapping("/{categoryId}")
	public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Integer categoryId) {
		
		ProductCategory createdProductCategory = productCategoryService.getProductCategoryById(categoryId);
		
		return new ResponseEntity<ProductCategory>(createdProductCategory , HttpStatus.OK);
	}
	
	@GetMapping("/get-by-name")
	public ResponseEntity<ProductCategory> getProductCategoryByName(@RequestParam String name) {
		
		ProductCategory createdProductCategory = productCategoryService.getProductCategoryByName(name);
		
		return new ResponseEntity<ProductCategory>(createdProductCategory , HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProductCategory>> getAllProductCategory() {
		
		List<ProductCategory> productCategories = productCategoryService.getAllProductCategory();
		
		return new ResponseEntity<List<ProductCategory>>(productCategories , HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{categoryId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<ProductCategory>deleteProductCategoryById(@PathVariable Integer categoryId) throws FeedbackException{
		
		
		
		return new ResponseEntity<ProductCategory>(productCategoryService.deleteProductCategory(categoryId) , HttpStatus.OK);
	}
	
	
	@PutMapping("/")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory) {
		
		
		return new ResponseEntity<ProductCategory>(productCategoryService.updateProductCategory(productCategory) , HttpStatus.OK);
	}
}
