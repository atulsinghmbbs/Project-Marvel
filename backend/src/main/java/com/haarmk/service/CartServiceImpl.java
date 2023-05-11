package com.haarmk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.haarmk.exception.CartException;
import com.haarmk.exception.HaarmkException;
import com.haarmk.exception.ProductException;
import com.haarmk.model.Cart;
import com.haarmk.model.CartItem;
import com.haarmk.model.Category;
import com.haarmk.model.Product;
import com.haarmk.model.User;
import com.haarmk.repository.CartRepo;
import com.haarmk.service.interfaces.CartItemService;
import com.haarmk.service.interfaces.CartService;
import com.haarmk.service.interfaces.CategoryService;
import com.haarmk.service.interfaces.CurrencyService;
import com.haarmk.service.interfaces.NameService;
import com.haarmk.service.interfaces.ProductService;
import com.haarmk.service.interfaces.UserService;
import com.haarmk.util.Utils;



@Service
public class CartServiceImpl implements CartService {
	@Autowired UserService userService;
	@Autowired ProductService productService;
	@Autowired CategoryService categoryService;
	@Autowired CartRepo cartRepo;
	@Autowired private CartItemService cartItemService;
	@Autowired private NameService nameService;
	@Autowired private CurrencyService currencyService;
	
	

	@Override
	public Cart addProduct(Integer productId, String username, JsonNode details) throws ProductException {
			User currentUser = userService.getUserByUsername(username);
			Product foundProduct =  productService.getProductById(productId);
			Boolean isProductPresent = currentUser.getCart().getItems().stream().anyMatch(e -> e.getProduct().getId() == foundProduct.getId());
			if(!isProductPresent) {
				CartItem cartItem = new CartItem();
				cartItem.setCart(currentUser.getCart());
				cartItem.setPrice(foundProduct.getPrice());
				cartItem.setProduct(foundProduct);
				cartItem.setQty(1);
				cartItem.setDetails(details);
				currentUser.getCart().getItems().add(cartItem);
				updateCartTotal(currentUser.getCart());
				currentUser = userService.updateUser(currentUser);
			}else {
				throw new CartException("This Item is already added! Product id: "+productId);
			}
			return currentUser.getCart();
	}
	


	@Override
	public Cart removeProduct(Long cartItemId, String username) throws ProductException {
		User currentUser = userService.getUserByUsername(username);
		CartItem cartItem = new CartItem();
		cartItem.setId(cartItemId);
		
		
		if(currentUser.getCart().getItems().contains(cartItem)) {
			currentUser.getCart().getItems().remove(cartItem);
			cartItemService.deleteCartItemById(cartItemId);
			updateCartTotal(currentUser.getCart());
			userService.updateUser(currentUser);
		}else {
			throw new CartException("Item does not exit in the cart. cart item id: "+cartItemId);
		}
		
		return currentUser.getCart();
	}
	
	@Override
	public Cart changeCartItemQty(Long cartItemId, Integer qty, String username) throws ProductException {
		User currentUser = userService.getUserByUsername(username);
		CartItem cartItem = new CartItem();
		cartItem.setId(cartItemId);
		if(currentUser.getCart().getItems().contains(cartItem)) {
			CartItem foundCartItem = cartItemService.getCartItemById(cartItemId);
			foundCartItem.setQty(qty);
			cartItemService.updateCartItem(foundCartItem);
			updateCartTotal(currentUser.getCart());
			
			userService.updateUser(currentUser);
			

			
		}else {
			throw new CartException("Item does not exit in the cart. cart item id: "+cartItemId);
		}
		
		return currentUser.getCart();
	}

	@Override
	public Cart getCart(String username) {
		User currentUser = userService.getUserByUsername(username);
		return currentUser.getCart();

	}

	@Override
	public Cart addDomainToCart(String domainName, String username) throws ProductException {
		Product foundProduct = null;
		
		try {
			foundProduct = productService.getProductByUniqueName(domainName);
		} catch (HaarmkException e) {
			Product newProduct = new Product();
			Category productCategory = categoryService.getCategoryByName("domain");
			JsonNode node = nameService.getPricingForDomain(domainName, 1);
			newProduct.setCategory(productCategory);
			newProduct.setUniqueName(domainName);			
			newProduct.setDisplayName(domainName);
			newProduct.setPrice(node.get("purchasePrice").asDouble());
			foundProduct = productService.addProduct(newProduct);
		}
		return this.addProduct(foundProduct.getId(), username,null);
		
	}



	@Override
	public Cart clearCart(String username) {
		User currentUser = userService.getUserByUsername(username);
		List<CartItem> cartItems = new ArrayList<>(currentUser.getCart().getItems());
		for(int i = 0; i < cartItems.size(); i++) {
			
			CartItem ci = cartItems.get(i);
			
			currentUser.getCart().getItems().remove(ci);
			userService.updateUser(currentUser);
			cartItemService.deleteCartItemById(ci.getId());
			
			System.out.println(ci.getId());
			
		}	
		
		
		updateCartTotal(currentUser.getCart());
		return currentUser.getCart();
	}
	
	@Override
	public Cart updateCartItemDetails(Long cartItemId, String username, JsonNode jsonNode) {
		User currentUser = userService.getUserByUsername(username);
		CartItem cartItem = new CartItem();
		cartItem.setId(cartItemId);
		if(currentUser.getCart().getItems().contains(cartItem)) {
			CartItem foundCartItem = cartItemService.getCartItemById(cartItemId);
			foundCartItem.setDetails(jsonNode);
			cartItemService.updateCartItem(foundCartItem);
			updateCartTotal(currentUser.getCart());
			
			userService.updateUser(currentUser);
			

			
		}else {
			throw new CartException("Item does not exit in the cart. cart item id: "+cartItemId);
		}
		
		return currentUser.getCart();
	}
	
	//this function sets all the prices from its source
	public void updateCartTotal(Cart cart) {
		
		Double total = 0.0;
		
		for(CartItem ci : cart.getItems()) {
			
			if((ci.getQty() <= 0))
				
				ci.setQty(1);
			
			Double itemPrice;
			
			if(ci.getProduct().getCategory().getName().equals("domain")) {
				
				
				JsonNode node = nameService.getPricingForDomain(ci.getProduct().getUniqueName(), ci.getQty());	
				
				itemPrice = currencyService.usdToInr(node.get("purchasePrice").asDouble()) + currencyService.getDomainMargin();
				
			}else {
				
				itemPrice = ci.getProduct().getPrice() * (Double.valueOf(ci.getQty()));
				
			}
			
			itemPrice = currencyService.roundToTwoDecimalPace(itemPrice);
			
			ci.setPrice(itemPrice);
			
			total+=itemPrice;
			
		}
		
		total = currencyService.roundToTwoDecimalPace(total);
		
		cart.setSubTotal(total);
		
	}



//	//this function calculates all the prices 
//	private void updateCartTotal(Cart cart) {
//		
//		Double total = 0.0;
//		
//		for(CartItem ci : cart.getItems()) {
//			
//			if( (ci.getQty() <= 0))
//				
//				ci.setQty(1);
//			
//			Double itemPrice;
//			
//			if(ci.getProduct().getCategory().getName().equals("domain")) {
//				
//				Double margin = 20D;
//				
////				JsonNode node = nameService.getPricingForDomain(ci.getProduct().getUniqueName(), ci.getQty());	
//				
//				itemPrice = ci.getProduct().getPrice()*(Double.valueOf(ci.getQty())) * currencyServiceImpl.getInrPrice() + margin;
//				
//			}else {
//				itemPrice = ci.getProduct().getPrice() * (Double.valueOf(ci.getQty()));
//			}
//			
//			
//			
//			itemPrice = (double) Math.round(itemPrice * 1000) / 1000;
//			
//			ci.setPrice(itemPrice);
//			
//			total+=itemPrice;
//			
//		}
//		
//		total = (double) Math.round(total * 100) / 100;
//		
//		cart.setSubTotal(total);
//		
//	}

	
	

}
