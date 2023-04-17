package com.haarmk.service.interfaces;

import java.util.List;

import com.haarmk.dto.OrderDto;
import com.haarmk.exception.OrderException;
import com.haarmk.model.Order;


public interface OrderService {

    public List<OrderDto> getAllOrders() throws OrderException;

    public OrderDto getOrderById(Long id)throws OrderException;

    public OrderDto addOrder(Order order)throws OrderException;

    public Order deleteOrder(Long id)throws OrderException;
}