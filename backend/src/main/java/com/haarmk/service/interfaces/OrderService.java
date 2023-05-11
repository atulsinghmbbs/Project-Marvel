package com.haarmk.service.interfaces;

import java.util.List;
import java.util.Set;

import com.haarmk.dto.OrderReqDto;
import com.haarmk.dto.OrderResDto;
import com.haarmk.exception.OrderException;
import com.haarmk.model.Address;
import com.haarmk.model.Orders;


public interface OrderService {

    Set<Orders> getAllOrders() throws OrderException;

    Orders getOrderById(Long id) throws OrderException;
    
    Orders getOrderByRazorpayOrderId(String razorpayOrderId) throws OrderException;

    Orders deleteOrder(Long id) throws OrderException;

//	Orders addOrder(OrderReqDto orderReqDto) throws OrderException;

	Orders addOrder(Address billingAddress) throws OrderException;
}