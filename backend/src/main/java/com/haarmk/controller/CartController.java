package com.haarmk.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.haarmk.model.Cart;
import com.haarmk.service.interfaces.CartService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/carts")
@SecurityRequirement(name = "bearer-key")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PutMapping("/add-item")
	public ResponseEntity<Cart> addProductById(@RequestParam Integer productId, Principal principal ){
		
		Cart cart = cartService.addProduct(productId,principal.getName());
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@PutMapping("/add-domain")
	public ResponseEntity<Cart> addDomain(@RequestParam String doamainName, Principal principal ){
		
		Cart cart = cartService.addDomainToCart(doamainName,principal.getName());
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@PutMapping("/remove-item")
	public ResponseEntity<Cart> removeProductById(@RequestParam Integer productId, Principal principal){
		
		Cart cart = cartService.removeProduct(productId, principal.getName());
		
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Cart> getCart(Principal principal){
		
		Cart cart = cartService.getCart(principal.getName());
		
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	


}
