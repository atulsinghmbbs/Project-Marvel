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
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.exception.FeedbackException;
import com.haarmk.model.Product;
import com.haarmk.model.ProductProperty;
import com.haarmk.service.interfaces.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "products")
public class ProductController {
	@Autowired ProductService productService;
	
	
	@PostMapping("/")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Product> addProduct(@Valid  @RequestBody Product product){

		Product createdProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(createdProduct , HttpStatus.CREATED);
	}
	


	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
		
		Product createdProduct = productService.getProductById(productId);
		
		return new ResponseEntity<Product>(createdProduct , HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProduct() {
		
		List<Product> productCategories = productService.getAllProduct();
		
		return new ResponseEntity<List<Product>>(productCategories , HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{categoryId}")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Product>deleteProductById(@PathVariable Integer categoryId) throws FeedbackException{
		
		
		
		return new ResponseEntity<Product>(productService.deleteProduct(categoryId) , HttpStatus.OK);
	}
	
	
	@PutMapping("/")
	@SecurityRequirement(name = "bearer-key")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		

		return new ResponseEntity<Product>(productService.updateProduct(product) , HttpStatus.OK);
	}

}
