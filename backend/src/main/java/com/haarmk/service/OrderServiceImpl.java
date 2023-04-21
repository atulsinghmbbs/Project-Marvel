package com.haarmk.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haarmk.dto.OrderDto;
import com.haarmk.exception.OrderException;
import com.haarmk.model.Orders;
import com.haarmk.repository.OrderRepo;
import com.haarmk.service.interfaces.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public List<OrderDto> getAllOrders() throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto getOrderById(Long id) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto addOrder(Orders order) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders deleteOrder(Long id) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Autowired
//    private OrderRepo orderRepo;
//
//	@Override
//	public List<OrderDto> getAllOrders() {
//      List<Order> list = orderRepo.findAll();
//      return list.stream().map(OrderDto::new).collect(Collectors.toList());
//	}
//
//	@Override
//	public OrderDto getOrderById(Long id) {
//		 return new OrderDto(orderRepo.findById(id)
//                 .orElseThrow(() -> new OrderException("Order not found Invalid order id...")));
//	}
//
//	@Override
//	public OrderDto addOrder(Order order) {
//		if(order!=null) {
//		Order orderSaved = orderRepo.save(order);
//         return new OrderDto(orderSaved);
//		}else {
//			throw new OrderException("order will not added... because order is null...");
//		}
//	}
//
//	@Override
//	public Order deleteOrder(Long id) {
//		  Optional<Order> order = orderRepo.findById(id);
//		  if(order.isPresent()) {
//			  orderRepo.deleteById(id);
//			  return order.get();
//		  }else {
//			  throw new OrderException("invalid order Id......");
//		  }
//	}

//    public OrderServiceImpl(OrderRepository repository) { //By me=========================
//        this.repository = repository;
//    }

}
