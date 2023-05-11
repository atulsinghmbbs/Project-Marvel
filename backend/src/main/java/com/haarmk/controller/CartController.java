package com.haarmk.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.haarmk.model.Cart;
import com.haarmk.service.interfaces.CartService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/carts")
@SecurityRequirement(name = "bearer-key")
public class CartController {

	@Autowired private CartService cartService;

	@PutMapping("/add-item")
	public ResponseEntity<Cart> addProductById(@RequestParam Integer productId, Principal principal ,@RequestBody JsonNode details){
		
		Cart cart = cartService.addProduct(productId,principal.getName(), details);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@PutMapping("/update-item-details")
	public ResponseEntity<Cart> updateCartItemDetails(@RequestParam Long cartItemId, Principal principal ,@RequestBody JsonNode details){
		
		Cart cart = cartService.updateCartItemDetails(cartItemId,principal.getName(), details);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@PutMapping("/add-domain")
	public ResponseEntity<Cart> addDomain(@RequestParam String domainName, Principal principal){
		
		Cart cart = cartService.addDomainToCart(domainName,principal.getName());
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@PutMapping("/remove-item")
	public ResponseEntity<Cart> removeProductById(@RequestParam Long productId, Principal principal){
		
		Cart cart = cartService.removeProduct(productId, principal.getName());
		
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	@PutMapping("/change-item-qty")
	public ResponseEntity<Cart> changeCartItemQty(@RequestParam Long cartItemId,Integer qty, Principal principal){
		
		Cart cart = cartService.changeCartItemQty(cartItemId,qty, principal.getName());
		
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Cart> getCart(Principal principal){
		
		Cart cart = cartService.getCart(principal.getName());
		

		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@PutMapping("/clear")
	public ResponseEntity<Cart> clearCart(Principal principal){
		
		Cart updatedCart = cartService.clearCart(principal.getName()); 
		return new ResponseEntity<Cart>(updatedCart, HttpStatus.OK);
		
	}
	


}
