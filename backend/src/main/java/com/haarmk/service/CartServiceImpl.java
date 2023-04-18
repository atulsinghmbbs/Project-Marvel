package com.haarmk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.exception.ProductException;
import com.haarmk.model.Product;
import com.haarmk.model.ShoppingCart;
import com.haarmk.repository.CartRepo;
import com.haarmk.service.interfaces.CartService;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cartRepo;
	
	@Override
	public Product addProduct(Product product,Integer userID) throws ProductException {
		
		Integer cartId = cartRepo.getCartIdByUserId(userID);
		
		Optional<ShoppingCart> cart =cartRepo.findById(cartId);
		
		if(cart.isPresent()) {
			List<Product>  list = new ArrayList<>();
			List<Product> products = cart.get().getProducts();
			int amount = 0;
			for(int i=0; i<products.size(); i++) {
				amount+=products.get(i).getPurchasePrice();
				
			}
			products.add(product);
			ShoppingCart newCart = new ShoppingCart(cartId, products);
			
			 cartRepo.save(newCart);
			 return product;
		}else {
			throw new ProductException("Invalid credentials....");
		}

	}

	@Override
	public List<Product> getAllProducts(Integer userID) throws ProductException {
		Integer cartId = cartRepo.getCartIdByUserId(userID);
		
		Optional<ShoppingCart> cart =  cartRepo.findById(cartId);
		
		if(cart.isPresent()) {
			List<Product> products = cart.get().getProducts();
			
			 return products;
		}else {
			throw new ProductException("Invalid userID....");
		}
	}

	@Override
	public Product deleteProductByProductId(Integer productId,Integer userID) throws ProductException {
		Integer cartId = cartRepo.getCartIdByUserId(userID);
		
		Optional<ShoppingCart> cart =cartRepo.findById(cartId);
		
		if(cart.isPresent()) {
			List<Product>  list = new ArrayList<>();
			List<Product> products = cart.get().getProducts();
			int amount=0;
			Product p=null ;
			for(int i=0; i<products.size(); i++) {
				if(products.get(i).getDomainId()!=productId) {
					list.add(products.get(i));
					amount+=products.get(i).getPurchasePrice();
					
				}else {
					p = products.get(i);
				}
				
			}
			ShoppingCart newCart = new ShoppingCart();
		
			
			 cartRepo.save(newCart);
			 return p;
		}else {
			throw new ProductException("Invalid credentials....");
		}

	}

}
