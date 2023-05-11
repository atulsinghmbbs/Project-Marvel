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
import com.haarmk.model.Category;
import com.haarmk.service.interfaces.CategoryService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
	@Autowired CategoryService productCategoryService;
	
	
	@PostMapping("/")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Category> addProductCategory(@Valid  @RequestBody Category productCategory){
		Category createdProductCategory = productCategoryService.addCategory(productCategory);
		
		return new ResponseEntity<Category>(createdProductCategory , HttpStatus.CREATED);
	}
	


	
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getProductCategoryById(@PathVariable Integer categoryId) {
		
		Category createdProductCategory = productCategoryService.getCategoryById(categoryId);
		
		return new ResponseEntity<Category>(createdProductCategory , HttpStatus.OK);
	}
	
	@GetMapping("/get-by-name")
	public ResponseEntity<Category> getProductCategoryByName(@RequestParam String name) {
		
		Category createdProductCategory = productCategoryService.getCategoryByName(name);
		
		return new ResponseEntity<Category>(createdProductCategory , HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllProductCategory() {
		
		List<Category> productCategories = productCategoryService.getAllCategory();
		
		return new ResponseEntity<List<Category>>(productCategories , HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{categoryId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Category>deleteProductCategoryById(@PathVariable Integer categoryId) throws FeedbackException{
		
		
		
		return new ResponseEntity<Category>(productCategoryService.deleteCategory(categoryId) , HttpStatus.OK);
	}
	
	
	@PutMapping("/")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Category> updateProductCategory(@RequestBody Category productCategory) {
		
		
		return new ResponseEntity<Category>(productCategoryService.updateCategory(productCategory) , HttpStatus.OK);
	}
}
