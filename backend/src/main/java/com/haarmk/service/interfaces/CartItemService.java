package com.haarmk.service.interfaces;

import java.util.Optional;

import com.haarmk.model.CartItem;

public interface CartItemService {
	CartItem getCartItemById(Long id);
	public void deleteCartItemById(Long id);
	CartItem updateCartItem(CartItem foundCartItem);
	Boolean isProductPresentInCart(Integer cartId, String uniqueName);
	Boolean isProductPresentInCart(String uniqueName);
}
