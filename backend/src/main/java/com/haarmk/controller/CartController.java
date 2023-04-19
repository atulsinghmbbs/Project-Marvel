package com.haarmk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.model.Product;
import com.haarmk.service.interfaces.CartService;

@RestController
@RequestMapping("/haarmk")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/addProductInCart/{userId}")
	public ResponseEntity<Product> AddProduct(@RequestBody Product product,@PathVariable("userId") Integer userId){
		
//		Product addProduct = cartService.addProduct(product,userId);
		
//		return new ResponseEntity<Product>(addProduct, HttpStatus.CREATED);
		return null;
	}
	
	@GetMapping("/getProductsFromCart/{userId}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable("userId") Integer userId){
		
		List<Product> products = cartService.getAllProducts(userId);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	

	@DeleteMapping("/deleteProductFromCart/{productId}/{userId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Integer productId,@PathVariable("userId") Integer userId){
		
//		Product product = cartService.deleteProductByProductId(productId,userId);
		
//		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
		return null;
	}
}
