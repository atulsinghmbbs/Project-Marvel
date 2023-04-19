package com.haarmk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.exception.ProductException;
import com.haarmk.model.Product;
import com.haarmk.repository.CartRepo;
import com.haarmk.service.interfaces.CartService;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepo cartRepo;
	
	@Override
	public Product addProduct(Integer productId) throws ProductException {
		
//		Integer cartId = cartRepo.getCartIdByUserId(userID);
//		
//		Optional<Cart> cart =cartRepo.findById(cartId);
//		
//		if(cart.isPresent()) {
//			List<Product>  list = new ArrayList<>();
//			List<Product> products = cart.get().getProducts();
//			int amount = 0;
//			for(int i=0; i<products.size(); i++) {
//				amount+=products.get(i).getPurchasePrice();
//				
//			}
//			products.add(product);
//			Cart newCart = new Cart(cartId, products);
//			
//			 cartRepo.save(newCart);
//			 return product;
//		}else {
//			throw new ProductException("Invalid credentials....");
//		}
		return null;

	}

	@Override
	public List<Product> getAllProducts(Integer userID) throws ProductException {
//		Integer cartId = cartRepo.getCartIdByUserId(userID);
//		
//		Optional<Cart> cart =  cartRepo.findById(cartId);
//		
//		if(cart.isPresent()) {
//			List<Product> products = cart.get().getProducts();
//			
//			 return products;
//		}else {
//			throw new ProductException("Invalid userID....");
//		}
		return null;
	}


	public Product deleteProductByProductId(Integer productId,Integer userID) throws ProductException {
//		Integer cartId = cartRepo.getCartIdByUserId(userID);
//		
//		Optional<Cart> cart =cartRepo.findById(cartId);
//		
		
//		if(cart.isPresent()) {
//			List<Product>  list = new ArrayList<>();
//			List<Product> products = cart.get().getProducts();
//			int amount=0;
//			Product p=null ;
//			for(int i=0; i<products.size(); i++) {
//				if(products.get(i).getDomainId()!=productId) {
//					list.add(products.get(i));
//					amount+=products.get(i).getPurchasePrice();
//					
//				}else {
//					p = products.get(i);
//				}
				
//			}
//			Cart newCart = new Cart();
//		
//			
//			 cartRepo.save(newCart);
//			 return null;
//		}else {
//			throw new ProductException("Invalid credentials....");
//		}
		return null;

	}

	@Override
	public Product removeProductByProductId(Integer productId, Integer userID) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

}
