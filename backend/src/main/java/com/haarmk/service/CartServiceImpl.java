package com.haarmk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.exception.HaarmkException;
import com.haarmk.exception.ProductException;
import com.haarmk.model.Cart;
import com.haarmk.model.Product;
import com.haarmk.model.ProductCategory;
import com.haarmk.model.User;
import com.haarmk.service.interfaces.CartService;
import com.haarmk.service.interfaces.ProductCategoryService;
import com.haarmk.service.interfaces.ProductService;
import com.haarmk.service.interfaces.UserService;


@Service
public class CartServiceImpl implements CartService {
	@Autowired UserService userService;
	@Autowired ProductService productService;
	@Autowired ProductCategoryService productCategoryService;
	

	@Override
	public Cart addProduct(Integer productId, String username) throws ProductException {
			User currentUser = userService.getUserByUsername(username);
			Product foundProduct =  productService.getProductById(productId);
			if(!currentUser.getCart().getProducts().contains(foundProduct)) {
			currentUser.getCart().getProducts().add(foundProduct);
				userService.updateUser(currentUser);
			}
			return currentUser.getCart();
	}

	@Override
	public Cart removeProduct(Integer productId, String username) throws ProductException {
		User currentUser = userService.getUserByUsername(username);
		Product foundProduct =  productService.getProductById(productId);
		if(currentUser.getCart().getProducts().contains(foundProduct)) {;
			currentUser.getCart().getProducts().remove(foundProduct);
			userService.updateUser(currentUser);
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
			foundProduct = productService.getProductByName(domainName);
		} catch (HaarmkException e) {
			Product newProduct = new Product();
			ProductCategory productCategory = productCategoryService.getProductCategoryByName("domain");
			newProduct.setCategory(productCategory);
			newProduct.setName(domainName);
			System.out.println(newProduct);
			foundProduct = productService.addProduct(newProduct);
		}
		return this.addProduct(foundProduct.getId(), username);
	}
	

}
