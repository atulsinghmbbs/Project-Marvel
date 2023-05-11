package com.haarmk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.haarmk.exception.CartException;
import com.haarmk.model.CartItem;
import com.haarmk.model.User;
import com.haarmk.repository.CartItemRepo;
import com.haarmk.service.interfaces.CartItemService;
import com.haarmk.service.interfaces.UserService;


@Service
public class CartItemServiceImpl implements CartItemService{
	@Autowired CartItemRepo cartItemRepo;
	@Autowired UserService userService;
	@Override
	public CartItem getCartItemById(Long id) {
		CartItem cartItem = cartItemRepo.findById(id).orElseThrow(()->new CartException("no cart item found by Id: "+id));
		
		return cartItem;
	}
	public void deleteCartItemById(Long id) {
		getCartItemById(id);
		cartItemRepo.deleteById(id);
		
		
	}
	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		getCartItemById(cartItem.getId());
		
		return cartItemRepo.save(cartItem);
	}
	@Override
	public Boolean isProductPresentInCart(Integer cartId, String uniqueName) {
		Optional<String> productUniqueName = cartItemRepo.isProductPresentInCart(cartId, uniqueName);
		if(productUniqueName.isPresent()) return true;
		else return false;
		
	}
	@Override
	public Boolean isProductPresentInCart(String uniqueName) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null) {
			return false;
		}
		String username = authentication.getName();
		if(username.equals("anonymousUser")) return false;
		User currentuser = userService.getUserByUsername(username);
		Integer cartId = currentuser.getCart().getId();
		Optional<String> productUniqueName = cartItemRepo.isProductPresentInCart(cartId, uniqueName);
		if(productUniqueName.isPresent()) return true;
		else return false;
		
	}
	
	

}
