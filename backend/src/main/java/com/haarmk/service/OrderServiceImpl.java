package com.haarmk.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.haarmk.dto.OrderItemReqDto;
import com.haarmk.dto.OrderReqDto;
import com.haarmk.dto.domain.DomainCheckData;
import com.haarmk.exception.HaarmkException;
import com.haarmk.exception.OrderException;
import com.haarmk.model.Address;
import com.haarmk.model.Cart;
import com.haarmk.model.CartItem;
import com.haarmk.model.OrderItem;
import com.haarmk.model.Orders;
import com.haarmk.model.Product;
import com.haarmk.model.Status;
import com.haarmk.model.User;
import com.haarmk.repository.OrderRepo;
import com.haarmk.service.interfaces.CartItemService;
import com.haarmk.service.interfaces.CartService;
import com.haarmk.service.interfaces.CurrencyService;
import com.haarmk.service.interfaces.NameService;
import com.haarmk.service.interfaces.OrderService;
import com.haarmk.service.interfaces.ProductService;
import com.haarmk.service.interfaces.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired private ProductService productService;
	@Autowired private NameService nameService;
	@Autowired private OrderRepo orderRepo;
	@Autowired private UserService userService;
	@Autowired private RazorpayClient razorpayClient;
	@Autowired private CurrencyService currencyService;
	@Autowired private CartService  cartService;
	
	@Override
	public Set<Orders> getAllOrders() throws OrderException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.getUserByUsername(username);
		
		return currentUser.getOrders();
	}
	
	@Override
	public Set<Orders> findAllForAdmin() throws OrderException {
		List<Orders> orders = orderRepo.findAll();
		if(orders.isEmpty()) throw new OrderException("No order is placed yet!");
		return new HashSet<>(orders);
	}

	@Override
	public Orders getOrderById(Long id) throws OrderException {
		// TODO Auto-generated method stub
		return orderRepo.findById(id).orElseThrow(()-> new OrderException("No order found by id: "+id));
	}

	@Override
	public Orders addOrder(Address billingAddress) throws OrderException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User currentUser = userService.getUserByUsername(username);
		
		Cart cart = currentUser.getCart();
		
		Double gstRate = 0.18;
		
		//this function sets all the prices from its source
		
		cartService.updateCartTotal( cart);
		
		Orders orders = new Orders();
		
		Double subTotal = currencyService.roundToTwoDecimalPace(cart.getSubTotal());
		
		Double tax = currencyService.roundToTwoDecimalPace(subTotal * gstRate);
		
		Double total = currencyService.roundToTwoDecimalPace(subTotal + tax);
		
		orders.setTax(tax);
		
		orders.setTotal(total);
		
		orders.setSubTotal(subTotal);
		
		orders.setUser(currentUser);
		
		orders.setBillingAddress(billingAddress);
		
		for(CartItem ci : currentUser.getCart().getItems()) {
			
			OrderItem  orderItem = new OrderItem();
			
			orderItem.setIsActivated(false);
			
			Product product = ci.getProduct();
			
			if(product.getCategory().getName().equals("domain")) {
				
				DomainCheckData domainCheckData = nameService.checkAvailability(product.getUniqueName());
				
				if(!domainCheckData.isPurchasable()) {
					
					throw new HaarmkException(product.getUniqueName()+" is not available.");
					
				}
			}
			
			orderItem.setDetails(ci.getDetails());
			
			orderItem.setProduct(product);
			
			orderItem.setQty(ci.getQty());
			
			orderItem.setPrice(ci.getPrice());
			
			orders.getItems().add(orderItem);
			
			orderItem.setOrder(orders);
			
		}
		Order order = null;
		try {
			  JSONObject orderRequest = new JSONObject();
			  orderRequest.put("amount", Math.round(total * 100)); // amount in the smallest currency unit
			  orderRequest.put("currency", "INR");
			  orderRequest.put("receipt", username);

			  order = razorpayClient.orders.create(orderRequest);
			} catch (RazorpayException e) {
			  // Handle Exception
			  System.out.println(e.getMessage());
			}
		orders.setRazorpayOrderId(order.get("id"));
//		orders.setRazorpayOrderId("dummy_order_id");
		orders.setStatus(Status.CREATED);
		currentUser.getOrders().add(orders);
		Orders savedOrder = orderRepo.save(orders);
		userService.updateUser(currentUser);
		return savedOrder;
	}

	@Override
	public Orders deleteOrder(Long id) throws OrderException {
		
		return null;
	}
	
	OrderItem createDomainOrderItem(String domainName, Integer years) {
		
		return null;
	}

	@Override
	public Orders getOrderByRazorpayOrderId(String razorpayOrderId) throws OrderException {
		
		return orderRepo.findByRazorpayOrderId(razorpayOrderId).orElseThrow(()-> new OrderException("No order found by razorpayOrderId: "+razorpayOrderId));
	}

	

}
