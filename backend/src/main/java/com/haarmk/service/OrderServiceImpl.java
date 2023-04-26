package com.haarmk.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.haarmk.dto.OrderReqDto;
import com.haarmk.dto.OrderResDto;
import com.haarmk.exception.OrderException;
import com.haarmk.model.Orders;
import com.haarmk.service.interfaces.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public List<OrderReqDto> getAllOrders() throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderReqDto getOrderById(Long id) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Orders addOrder(OrderReqDto orderReqDto) throws OrderException {
		
		return null;
	}

	@Override
	public Orders deleteOrder(Long id) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}



}
